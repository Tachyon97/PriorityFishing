package com;

import com.tasks.FishTask;
import com.tasks.InventoryTask;
import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;

import java.awt.*;

@ScriptManifest(category = Category.FISHING, name = "Code[ Fisher", author = "Farhad", version = 1.1)
public class FishingMain extends AbstractScript {


    private Timer runTime = new Timer();
    private String type;
    private FontMetrics fontMetrics;

    private TaskManager manager;


    @Override
    public void onPaint(Graphics g) {
        drawText(g, "Code[ Fisher 1.0", 10, 50, Color.CYAN);
        drawText(g, "StartLevel: " + SkillTracker.getStartLevel(Skill.FISHING) + " (+" + SkillTracker.getGainedLevels(Skill.FISHING) + ")", 10, 72, Color.CYAN);
        drawText(g, "Gained Xp: " + SkillTracker.getGainedExperience(Skill.FISHING) + " (" + SkillTracker.getGainedExperiencePerHour(Skill.FISHING) + ")", 10, 94, Color.CYAN);

        drawText(g, "Elapsed Time: " + Timer.formatTime(runTime.elapsed()), 10, 116, Color.CYAN);
        drawText(g, "Caught: " + (SkillTracker.getGainedExperience(Skill.FISHING) / 120) + " (" + (SkillTracker.getGainedExperiencePerHour(Skill.FISHING) / 120) + ")", 10, 138, Color.CYAN);
        int x = NPCs.closest(6825).getBoundingBox().x;
        int y = NPCs.closest(6825).getBoundingBox().y;
        drawText(g, "Lure", x, y - 20, Color.orange);
        // g.drawString("Type: Anglerfish. Mode: Banking", 10, 368);
    }

    @Override
    public int onLoop() {
        try {
            manager.getNextTask().execute();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 500;
    }

    private void simpleAntiban() {
        if (!Inventory.isFull() && Players.localPlayer().isAnimating())
            Mouse.moveMouseOutsideScreen();
    }

    @Override
    public void onStart() {
        SkillTracker.start();
        manager = new TaskManager();
        manager.addTasks(new FishTask());
        manager.addTasks(new InventoryTask());
        log("Added " + FishTask.class.getSimpleName() + ", type: " + type);
        log("Added " + InventoryTask.class.getSimpleName() + ", type: " + type);
    }

    public void drawText(Graphics g, String text, int x, int y, Color c) {
        Graphics2D g2 = (Graphics2D) g;
        Color color3 = new Color(0, 0, 0, 255);
        Font font1 = new Font("Arial", 1, 12);
        g.setFont(font1);
        fontMetrics = g.getFontMetrics();
        Rectangle textBox = new Rectangle(x, y - g.getFont().getSize(),
                (int) fontMetrics.getStringBounds(text, g).getWidth() + 8,
                (int) fontMetrics.getStringBounds(text, g).getHeight() + 6);
        Paint defaultPaint = g2.getPaint();
        g2.setPaint(new RadialGradientPaint(
                new Point.Double(textBox.x + textBox.width / 2.0D, textBox.y + textBox.height / 2.0D),
                (float) (textBox.getWidth() / 2.0D), new float[]{0.5F, 1.0F},
                new Color[]{new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), 255),
                        new Color(0.0F, 0.0F, 0.0F, 0.8F)}));
        g.fillRect(textBox.x, textBox.y + 12, textBox.width, textBox.height);
        g2.setPaint((Paint) defaultPaint);
        g.setColor(Color.BLACK);
        g.drawRect(textBox.x, textBox.y + 12, textBox.width, textBox.height);
        g.setColor(c);
        g.drawString(text, x + 4, y + 15);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                g.setColor(new Color(255, 255, 255));
                g.drawString("" + text.charAt(i), x + fontMetrics.stringWidth(text.substring(0, i)) + 4, y + 15);
            }
        }
    }

}
