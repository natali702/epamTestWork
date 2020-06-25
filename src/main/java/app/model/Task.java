package app.model;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String title;
    private String description;
    public Integer getId() {
        return null;
    }

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Task() {
    }
}
