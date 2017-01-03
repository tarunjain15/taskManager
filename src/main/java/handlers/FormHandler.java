package handlers;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import types.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tarujain on 12/29/16.
 */
public class FormHandler {
    private static final String TASK_COUNT_ATTR = "taskCount";
    private static final String DELETED_TASKS = "removedTasks";
    public static String getTaskCountAndDelete(MultiValueMap<String,String> form) {
        String taskCount = form.get(TASK_COUNT_ATTR).get(0);
        form.remove(TASK_COUNT_ATTR);
        return taskCount;
    }

    public static List<String> getDeletedTasksAndDelete(MultiValueMap<String,String> form) {
        List<String> deletedTask = form.get(DELETED_TASKS);
        final List<String> deletedTaskList = Arrays.stream(deletedTask.get(0).split(",")).collect(Collectors.toList());
        form.remove(DELETED_TASKS);
        return deletedTaskList;
    }

    public static List<Task> getUpdatedTaskList(MultiValueMap<String,String> form, List<String> deleteTask) {
        String[] keys = form.keySet().stream().map(r -> r.split("_")[1]).distinct().toArray(String[]::new);
        List<Task> taskList = new ArrayList();
        for (String key : keys) {
            if( StringUtils.isEmpty(form.get("desc_"+key).get(0))) {
                deleteTask.add(key);
                continue;
            }
            Task task = new Task(
                    key,
                    getFormElement(form,"desc",key),
                    getFormElement(form,"time",key),
                    getFormElement(form,"date",key),
                    getFormElement(form,"excuse",key),
                    Boolean.parseBoolean(getFormElement(form,"done",key)),
                    getFormElement(form,"project",key)
            );
            taskList.add(task);
        }
        return taskList;
    }
    private static String getFormElement(MultiValueMap<String, String> form, String name, String key){
        List<String> values = form.get(name+"_"+key);
        if(values==null || values.get(0)==null){
            return "";
        }
        return values.get(0);
    }
}