package app.model;

public class Goal {
    private long id;
    private String title;
    private long parentId;
    private Goal parent;

    public Goal(long id, String title, long parentId) {
        this(id, title);
        this.parentId = parentId;
    }

    public Goal(String title, long parentId) {
        this(title);
        this.parentId = parentId;
    }

    public Goal(String title) {
        this.title = title;
    }

    public Goal(long id, String title) {
        this(title);
        this.id = id;
    }

    public Goal(long id, String title, long parentId, Goal parent) {
        this(id, title, parentId);
        this.parent = parent;
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

    @SuppressWarnings("unused")
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @SuppressWarnings("unused")
    public Goal getParent() {
        return parent;
    }

    @SuppressWarnings("unused")
    public void setParent(Goal parent) {
        this.parent = parent;
    }
}
