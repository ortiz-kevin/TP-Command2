# Conçu par Kevin ORTIZ

import java.util.ArrayList;
import java.util.List;

// Light (Lumière)
class Light {
    private boolean isOn;
    private int brightness;

    public void turnOn() {
        isOn = true;
        System.out.println("Light is on");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Light is off");
    }

    public void adjustBrightness(int intensity) {
        brightness = intensity;
        System.out.println("Brightness adjusted to " + intensity);
    }

    public int getBrightness() {
        return brightness;
    }
}


// LightControlPanel (Panneau de Contrôle d'Éclairage)
class LightControlPanel {
    private List<Light> lights;

    public LightControlPanel() {
        lights = new ArrayList<>();
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.remove(light);
    }
}

// Command (Commande)
interface Command {
    void execute();
    void undo();
}

// TurnOnLightCommand (Commande pour Allumer la Lumière)
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// TurnOffLightCommand (Commande pour Éteindre la Lumière)
class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

// AdjustBrightnessCommand (Commande pour Régler l'Intensité Lumineuse)
class AdjustBrightnessCommand implements Command {
    private Light light;
    private int previousBrightness;
    private int newBrightness;

    public AdjustBrightnessCommand(Light light, int newBrightness) {
        this.light = light;
        this.newBrightness = newBrightness;
    }

    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.adjustBrightness(newBrightness);
    }

    @Override
    public void undo() {
        light.adjustBrightness(previousBrightness);
    }
}

// Classe de test
public class LightingControlSystemTest {
    public static void main(String[] args) {
        // Création d'une lampe
        Light light = new Light();

        // Création d'un panneau de contrôle d'éclairage
        LightControlPanel controlPanel = new LightControlPanel();
        controlPanel.addLight(light);

        // Création des commandes
        Command turnOnCommand = new TurnOnLightCommand(light);
        Command turnOffCommand = new TurnOffLightCommand(light);
        Command adjustBrightnessCommand = new AdjustBrightnessCommand(light, 50);

        // Exécution des commandes
        turnOnCommand.execute();
        adjustBrightnessCommand.execute();
        turnOffCommand.execute();

        // Annulation des commandes
        turnOffCommand.undo();
        adjustBrightnessCommand.undo();
        turnOnCommand.undo();
    }
}
