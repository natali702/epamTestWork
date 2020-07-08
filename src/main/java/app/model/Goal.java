package app.model;

import java.util.List;

public class Goal {
    private long id;
    private String title;
    private long parentId;
    private List<Goal> Childs;

    public Goal(long id, String title, long parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
    }

    public Goal(String title, long parentId) {
        this.title = title;
        this.parentId = parentId;
    }

    public Goal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<Goal> getChilds() {
        return Childs;
    }

    public void setChilds(List<Goal> childs) {
        Childs = childs;
    }
}
