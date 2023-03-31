package com.tasks;

import com.Task;
import com.framework.Priority;
import com.framework.annotations.After;
import com.framework.annotations.PrioritisedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;

@PrioritisedTask(priority = Priority.NOW)
@After(task = InventoryTask.class, priority = Priority.LOW)
public class InventoryTask extends Task {


    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() throws InterruptedException {
        Inventory.dropAllExcept("Feather", "Fly fishing rod");
        return Calculations.random(250, 450);
    }
}
