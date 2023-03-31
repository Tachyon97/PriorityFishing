/**
 * Represents a task that can contain sub-tasks.
 */
public class TaskObject {
    // Private fields with getters and setters
    private String taskName;
    private ArrayList<TaskObject> subTasks;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ArrayList<TaskObject> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<TaskObject> subTasks) {
        this.subTasks = subTasks;
    }

    /**
     * Constructor for a new TaskObject.
     * @param taskName The name of the task.
     */
    public TaskObject(String taskName) {
        this.taskName = taskName;
        this.subTasks = new ArrayList<>();
    }

    /**
     * Adds a sub-task to the current task.
     * @param task The sub-task to add.
     */
    public void addSubTask(TaskObject task) {
        this.subTasks.add(task);
    }

    /**
     * Checks if the current task has any sub-tasks.
     * @return True if the current task has sub-tasks, false otherwise.
     */
    public boolean hasSubTasks() {
        return !this.subTasks.isEmpty();
    }

    /**
     * Overrides the default equals method to compare two TaskObjects based on their task name.
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskObject)) {
            return false;
        }
        TaskObject other = (TaskObject) obj;
        return this.taskName.equals(other.taskName);
    }

    /**
     * Overrides the default hashCode method to hash TaskObjects based on their task name.
     * @return The hash code of the TaskObject.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.taskName);
    }
}
