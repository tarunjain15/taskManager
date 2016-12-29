package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

@Controller
public class RootController {
    private static final Type TASK_TYPE = new TypeToken<List<Task>>() {}.getType();
    private static final String fileName = "./target/classes/static/data.json";
    @GetMapping("/inbox")
    public String getInbox(Model model) {
        try {
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
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

}

