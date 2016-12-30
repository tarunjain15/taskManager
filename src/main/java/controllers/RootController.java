package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RootController {
    private static final Type TASK_TYPE = new TypeToken<List<Task>>() {}.getType();
    private static final String fileName = "./target/classes/static/data.json";
    @GetMapping("/inbox")
    public String getInbox(Model model) {
        try {
            final JsonReader json = new JsonReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
            final List<Task> taskList = new Gson().fromJson(
                    json,
                    TASK_TYPE);
            model.addAttribute("taskList",taskList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "rootView";
    }

    @RequestMapping(value="/inbox", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitTasks(HttpServletRequest request, Model model) {
        Map<String, String[]> form = request.getParameterMap();
        String[] keys = form.keySet().stream().map(r -> r.split("_")[1]).distinct().toArray(String[]::new);
        List<Task> taskList = new ArrayList();
        for (String key : keys) {
            if( StringUtils.isEmpty(form.get("desc_"+key)[0])) {
                continue;
            }
            Task task = new Task(key,form.get("desc_"+key)[0],form.get("time_"+key)[0],form.get("date_"+key)[0],form.get("excuse_"+key)[0]);
            taskList.add(task);
        }
        String json = new Gson().toJson(taskList);
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("taskList",taskList);
        return "rootView";
    }
}

