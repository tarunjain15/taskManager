package controllers;

/**
 * Created by tarujain on 12/29/16.
 */

import dao.TaskListDao;
import handlers.FilterHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class DateController {




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
//        return "redirect:/inbox.html";
//    }
}