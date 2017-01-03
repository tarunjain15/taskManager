package controllers;

import com.google.gson.Gson;
import dao.TaskListDao;
import handlers.FilterHandler;
import handlers.FormHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import types.Task;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InboxController {

    @GetMapping("/inbox")
    public String getInbox(Model model) {
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        List<Task> allTasks = TaskListDao.getTaskList();
        List<Task> tasks = FilterHandler.filterByDate(allTasks,"");
        tasks = FilterHandler.getIncompleted(tasks);
        model.addAttribute("taskList",tasks);
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }

    private List<String> getProjectList(List<Task> tasks) {
        List<String> projects = tasks.stream().map(r->r.getProject()).filter(r-> !StringUtils.isEmpty(r)).distinct().collect(Collectors.toList());
        return projects;
    }

    @GetMapping("/listall")
    public String getAllTasks(Model model) {
        List<Task> allTasks = TaskListDao.getTaskList();
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList", allTasks);
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }


    @GetMapping("/date")
    public String getDatePage(@RequestParam(value = "date", defaultValue = "today", required=false) String date, Model model) {
        if("today".equals(date)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.now();
            date = dtf.format(localDate);
        }
        List<Task> allTasks = TaskListDao.getTaskList();
        List<Task> tasks = FilterHandler.filterByDate(allTasks,date);
        tasks = FilterHandler.getIncompleted(tasks);
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList", tasks);
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }

    @GetMapping("/homepage")
    public String getHomePage() {
        return "homepage";
    }

    @GetMapping("/completed")
    public String getCompleted(Model model) {
        List<Task> allTasks = TaskListDao.getTaskList();
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList",FilterHandler.getCompleted(allTasks));
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }

    @GetMapping("/incomplete")
    public String getIncompleted(Model model) {
        List<Task> allTasks = TaskListDao.getTaskList();
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList",FilterHandler.getIncompletedTillDate(allTasks));
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }

    @GetMapping("/project")
    public String getProject(@RequestParam(value = "project", defaultValue = "", required=false) String project, Model model) {
        List<Task> allTasks = TaskListDao.getTaskList();
        model.addAttribute("taskCount", TaskListDao.getTaskCount());
        model.addAttribute("taskList",FilterHandler.getTasksForProject(allTasks, project));
        model.addAttribute("projectList", getProjectList(allTasks));
        return "rootView";
    }

    @RequestMapping(value="/inbox", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitTasks(@RequestBody MultiValueMap<String,String> form, HttpServletRequest request, Model model) {
        String taskCount = FormHandler.getTaskCountAndDelete(form);
        List<String> deletedTask = FormHandler.getDeletedTasksAndDelete(form);
        List<Task> oldTaskList = TaskListDao.getTaskList();
        List<Task> formTaskList = FormHandler.getUpdatedTaskList(form, deletedTask);

        String taskListJson = new Gson().toJson(createNewTaskList(oldTaskList,formTaskList,deletedTask));
        TaskListDao.writeTaskList(taskListJson);

        TaskListDao.writeTaskCount(taskCount);
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }

    private List<Task> createNewTaskList(List<Task> oldTaskList, List<Task> formTaskList, List<String> deletedTask) {
        HashMap<String, Task> taskMap = new HashMap<String, Task>();
        oldTaskList.stream().filter(t->!deletedTask.contains(t.getId())).forEach(t->taskMap.put(t.getId(),t));
        formTaskList.stream().filter(t->!deletedTask.contains(t.getId())).forEach(t->taskMap.put(t.getId(),t));
        return taskMap.values().stream().collect(Collectors.toList());
    }
}

