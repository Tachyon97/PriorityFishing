package com.tasks;

import com.Task;
import com.framework.Priority;
import com.framework.annotations.After;
import com.framework.annotations.PrioritisedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;

import static org.dreambot.api.methods.MethodProvider.sleepUntil;

@PrioritisedTask(priority = Priority.HIGH)
@After(task = FishTask.class, priority = Priority.LOW)
public class FishTask extends Task {


    @Override
    public boolean validate() {
        return !Inventory.isFull() && NPCs.closest("Rod Fishing spot").exists() && !Players.localPlayer().isAnimating();
    }

    @Override
    public int execute() throws InterruptedException {
        NPCs.closest("Rod Fishing spot").interact("Lure");
        sleepUntil(() -> Players.localPlayer().isAnimating(), 1500);
        return Calculations.random(250, 450);
    }
}
