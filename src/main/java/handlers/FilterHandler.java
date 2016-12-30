package handlers;
import types.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tarujain on 12/29/16.
 */
public class FilterHandler {
    public static List<Task> filterByDate(List<Task> taskList, String date) {
        return taskList.stream().filter((task -> ((task.getDate().trim()).equalsIgnoreCase(date)))).collect(Collectors.toList());
    }

}