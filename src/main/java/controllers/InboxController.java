package controllers;

import com.google.gson.Gson;
import dao.TaskListDao;
import handlers.FilterHandler;
import handlers.FormHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import types.Task;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InboxController {

    @GetMapping("/inbox")
    public String getInbox(Model model) {
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList",FilterHandler.filterByDate(TaskListDao.getTaskList(),""));
        return "rootView";
    }
    @GetMapping("/listall")
    public String getAllTasks(Model model) {
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList", TaskListDao.getTaskList());
        return "rootView";
    }
    @GetMapping("/date")
    public String getDatePage(@RequestParam("date") String date, Model model) {
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList", FilterHandler.filterByDate(TaskListDao.getTaskList(), date.trim()));
        return "rootView";
    }
    @RequestMapping(value="/inbox", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitTasks(@RequestBody MultiValueMap<String,String> form, Model model) {
        String taskCount = FormHandler.getTaskCountAndDelete(form);
        List<Task> oldTaskList = TaskListDao.getTaskList();
        List<Task> formTaskList = FormHandler.getUpdatedTaskList(form);

        String taskListJson = new Gson().toJson(createNewTaskList(oldTaskList,formTaskList));
        TaskListDao.writeTaskList(taskListJson);

        TaskListDao.writeTaskCount(taskCount);

        return "redirect:/inbox.html";
    }

    private List<Task> createNewTaskList(List<Task> oldTaskList, List<Task> formTaskList) {
        HashMap<String, Task> taskMap = new HashMap<String, Task>();
        oldTaskList.stream().forEach(t->taskMap.put(t.getId(),t));
        formTaskList.stream().forEach(t->taskMap.put(t.getId(),t));
        return taskMap.values().stream().collect(Collectors.toList());
    }
}

