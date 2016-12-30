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

import java.util.List;

@Controller
public class InboxController {

    @GetMapping("/inbox")
    public String getInbox(Model model) {
        System.out.println("==========");
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList",TaskListDao.getTaskList());
        return "rootView";
    }


    @RequestMapping(value="/inbox", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitTasks(@RequestBody MultiValueMap<String,String> form, Model model) {
        String taskCount = FormHandler.getTaskCountAndDelete(form);

        List<Task> taskList = FormHandler.getUpdatedTaskList(form);
        String taskListJson = new Gson().toJson(taskList);
        TaskListDao.writeTaskList(taskListJson);

        TaskListDao.writeTaskCount(taskCount);

        model.addAttribute("taskList", FilterHandler.filterByDate(taskList,""));
        model.addAttribute("taskCount", taskCount);
        return "rootView";
    }
}

