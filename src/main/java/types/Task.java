package types;

public class Task {

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    private String id;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private String time;
    private String date;
    private String excuse;
    private boolean done;
    private String project;

    public Task(String id, String desc, String time, String date, String excuse, boolean done, String project) {
        this.id = id;
        this.desc = desc;
        this.time = time;
        this.date = date;
        this.excuse = excuse;
        this.done = done;
        this.project = project;
    }
}

