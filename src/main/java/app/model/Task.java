package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

//@JsonPropertyOrder({"id", "title", "description"})
@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private String username;
    private LocalDate taskDate;
    private boolean status;
    private Goal goal;

    public Task(Long id, String title, String description, String username, LocalDate taskDate, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.taskDate = taskDate;
        this.status = status;
    }

    public Task(String title, String description, String username, LocalDate taskDate, boolean status) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.taskDate = taskDate;
        this.status = status;
    }

    protected Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
