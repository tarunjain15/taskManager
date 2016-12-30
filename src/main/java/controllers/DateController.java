package controllers;

/**
 * Created by tarujain on 12/29/16.
 */

import com.google.gson.Gson;
import dao.TaskListDao;
import handlers.FormHandler;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import types.Task;

import java.util.List;
public class DateController {

//    @GetMapping("/date")
//    public String getInbox(Model model) {
//        List<Task> taskList = TaskListDao.getTaskList();
//
//        model.addAttribute("taskCount", TaskListDao.getTaskCount());
//        model.addAttribute("taskList", taskList );
//        return "rootView";
//    }
//
//
//    @RequestMapping(value = "/date", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String submitTasks(@RequestBody MultiValueMap<String, String> form, Model model) {
//        String taskCount = FormHandler.getTaskCountAndDelete(form);
//
//        List<Task> taskList = FormHandler.getUpdatedTaskList(form);
//        String taskListJson = new Gson().toJson(taskList);
//
//        TaskListDao.writeTaskList(taskListJson);
//        TaskListDao.writeTaskCount(taskCount);
//
//        model.addAttribute("taskList", taskList);
//        model.addAttribute("taskCount", taskCount);
//        return "rootView";
//    }
}