package com;

import org.dreambot.api.script.AbstractScript;

public abstract class Task {
    protected AbstractScript s;

    public Task(AbstractScript s) {
        this.s = s;
    }

    public Task() {

    }

    public abstract boolean validate();

    public abstract int execute() throws InterruptedException;
}
