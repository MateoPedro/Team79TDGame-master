package controller;

import model.Wizard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Wizard controller class
 */
public class WizardController {
    private ArrayList<Wizard> wizards;
    private HashMap<Wizard, Integer> cooldowns;
    private int numWizards;

    public WizardController() {
        wizards = new ArrayList<>();
        cooldowns = new HashMap<>();
        numWizards = 0;
    }

    public void reset() {
        wizards = new ArrayList<>();
        cooldowns = new HashMap<>();
        numWizards = 0;
    }

    public void addWizard(Wizard wizard) {
        wizards.add(wizard);
        cooldowns.put(wizard, wizard.getAttackDelay());
        numWizards++;
    }

    public boolean updateCooldown(Wizard wizard) {
        if (cooldowns.containsKey(wizard)) {
            if (cooldowns.get(wizard) > 0) {
                cooldowns.put(wizard, cooldowns.get(wizard) - 1);
            } else if (cooldowns.get(wizard) == 0) {
                cooldowns.put(wizard, wizard.getAttackDelay());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Wizard> getWizards() {
        return wizards;
    }

    public HashMap<Wizard, Integer> getCooldowns() {
        return cooldowns;
    }

    public int getNumWizards() {
        return numWizards;
    }
}
