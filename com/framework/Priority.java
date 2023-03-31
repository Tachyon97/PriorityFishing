package com.framework;

public enum Priority {
    LOW(0),
    DEFAULT(1),
    HIGH(2),
    NOW(3);

    private int priority;
    Priority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
}
