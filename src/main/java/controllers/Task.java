package controllers;

public class Task {

    private final String id;
    private final String desc;
    private final String time;
    private final String date;
    private final String excuse;

    public Task(String id, String desc, String time, String date, String excuse) {
        this.id = id;
        this.desc = desc;
        this.time = time;
        this.date = date;
        this.excuse = excuse;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getExcuse() {
        return excuse;
    }

    public String getDesc() {
        return desc;
    }

}

