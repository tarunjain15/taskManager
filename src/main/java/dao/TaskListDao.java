package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import types.Project;
import types.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarujain on 12/29/16.
 */
public class TaskListDao {
    private static final String TASK_COUNT = "./taskCount.txt";
    private static final Type TASK_TYPE = new TypeToken<List<Task>>() {}.getType();
    private static final String TASK_LIST_JSON = "./taskList.json";

    private static final String PROJECT_COUNT = "./ProjectCount.txt";
    private static final Type PROJECT_TYPE = new TypeToken<List<Project>>() {}.getType();
    private static final String PROJECT_LIST_JSON = "./ProjectList.json";

    public static int getTaskCount() {
        File file = new File(TASK_COUNT);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = reader.readLine();
            return ( text != null)?(Integer.parseInt(text)):0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return 0;
    }
    public static List<Task> getTaskList() {
        try {
            final JsonReader json = new JsonReader(new InputStreamReader(
                    new FileInputStream(TASK_LIST_JSON), "UTF-8"));
            return (new Gson().fromJson(json, TASK_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }
    public static void writeTaskList(String json) {
        writeInFile(json, TASK_LIST_JSON);
    }
    public static void writeTaskCount(String taskCount) {
        writeInFile(taskCount, TASK_COUNT);
    }
    private static void writeInFile(String json, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getProjectCount() {
        File file = new File(PROJECT_COUNT);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = reader.readLine();
            return ( text != null)?(Integer.parseInt(text)):0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return 0;
    }
    public static List<Project> getProjectList() {
        try {
            final JsonReader json = new JsonReader(new InputStreamReader(
                    new FileInputStream(PROJECT_LIST_JSON), "UTF-8"));
            return (new Gson().fromJson(json, PROJECT_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public static void writeProjectCount(String projectCount) {
        writeInFile(projectCount, PROJECT_COUNT);
    }
}
