package com;

import com.framework.TaskObject;
import org.dreambot.api.script.AbstractScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private Task lastTask = null;
    private static List<TaskObject> tasks = new ArrayList<>();

    public Task EMPTY = new Task() {
        @Override
        public boolean validate() {
            return true;
        }

        @Override
        public int execute() throws InterruptedException {
            System.out.println("No tasks active");
            return 0;
        }
    };

    private Comparator<TaskObject> comparator = (one, two) -> Integer.compare(two.getPriority(this), one.getPriority(this));

    public Task getLastTask() {
        return lastTask;
    }

    public void addTasks(Task task) {
        tasks.add(new TaskObject(task));
    }

    public Task getNextTask() {
        Collections.sort(tasks, comparator);
        for (TaskObject object : tasks) {
            lastTask = object.getTask();
            return object.getTask();
        }
        return EMPTY;
    }
}
