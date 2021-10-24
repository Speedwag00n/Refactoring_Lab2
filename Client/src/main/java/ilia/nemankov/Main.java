package ilia.nemankov;

import ilia.nemankov.client.ConsoleWizard;
import ilia.nemankov.client.ConsoleWizardImpl;

public class Main {

    public static void main(String[] args) {
        ConsoleWizard consoleWizard = new ConsoleWizardImpl();
        consoleWizard.serve();
    }

}
