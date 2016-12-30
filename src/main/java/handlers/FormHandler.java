package handlers;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import types.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarujain on 12/29/16.
 */
public class FormHandler {
    private static final String TASK_COUNT_ATTR = "taskCount";
    public static String getTaskCountAndDelete(MultiValueMap<String,String> form) {
        String taskCount = form.get(TASK_COUNT_ATTR).get(0);
        form.remove(TASK_COUNT_ATTR);
        return taskCount;
    }

    public static List<Task> getUpdatedTaskList(MultiValueMap<String,String> form) {
        String[] keys = form.keySet().stream().map(r -> r.split("_")[1]).distinct().toArray(String[]::new);
        List<Task> taskList = new ArrayList();
        for (String key : keys) {
            if( StringUtils.isEmpty(form.get("desc_"+key).get(0))) {
                continue;
            }
            Task task = new Task(key,form.get("desc_"+key).get(0),form.get("time_"+key).get(0),form.get("date_"+key).get(0),form.get("excuse_"+key).get(0));
            taskList.add(task);
        }
        return taskList;
    }
}
