package com.framework;

import com.Task;
import com.TaskManager;
import com.framework.annotations.After;
import com.framework.annotations.PrioritisedTask;

import java.util.HashMap;
import java.util.Map;

public class TaskObject {
    private int defaultPriority;
    private Map<Class<? extends Task>, Integer> priorityMapping = new HashMap<>();
    private Task referencedTask;

    public TaskObject(Task task) {
        referencedTask = task;

        Class<? extends Task> referencedTaskClass = referencedTask.getClass();
        PrioritisedTask taskAnnotation = referencedTaskClass.getAnnotation(PrioritisedTask.class);
        defaultPriority = taskAnnotation.priority().getPriority();

        if (referencedTaskClass.isAnnotationPresent(After.class)) {
            After priority = referencedTaskClass.getAnnotation(After.class);
            priorityMapping.put(priority.task(), priority.priority().getPriority());
        }
    }

    public Task getTask() {
        return referencedTask;
    }

    public int getPriority(TaskManager object) {
        return object.getLastTask() == null ? defaultPriority : priorityMapping.getOrDefault(object.getLastTask().getClass(), defaultPriority);
    }


}

