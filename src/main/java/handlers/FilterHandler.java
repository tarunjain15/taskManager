package handlers;
import types.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tarujain on 12/29/16.
 */
public class FilterHandler {
    public static List<Task> filterByDate(List<Task> taskList, String date) {
        return taskList.stream().filter((task -> ((task.getDate().trim()).equalsIgnoreCase(date)))).collect(Collectors.toList());
    }

    public static List<Task> getCompleted(List<Task> taskList) {
        return taskList.stream().filter((task -> (task.isDone()))).collect(Collectors.toList());
    }
    public static List<Task> getIncompleted(List<Task> taskList) {
        return taskList.stream().filter((task -> !(task.isDone()))).collect(Collectors.toList());
    }

    public static List<Task> getIncompletedTillDate(List<Task> taskList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate);
        return taskList.stream().filter(task ->(!task.isDone() && isPreviousDate(task.getDate(), date))).collect(Collectors.toList());
    }
    private static boolean isPreviousDate(String taskDate, String currDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date curr = null;
        Date task = null;
        try {
            task = sdf.parse(taskDate);
            curr = sdf.parse(currDate);
        } catch (ParseException e) {
            return false;
        }
        return (curr.compareTo(task) >= 0);

    }
}