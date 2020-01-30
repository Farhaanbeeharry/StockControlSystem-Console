package stockcontrolsystem;

import javax.swing.JOptionPane;

public class InputData {

    public static int inputInt(String message) {

        String input;
        int number = -1;

        while (number == -1) {
            input = JOptionPane.showInputDialog(message);
            if (input == null) {
                return -1;
            }
            number = stringToInt(input);
        }

        return number;
    }

    private static int stringToInt(String input) {

        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input !\nPress ENTER to try again !");
            return -1;
        }
    }

    public static double inputDouble(String message) {

        String input;
        double number = -1;

        while (number == -1) {
            input = JOptionPane.showInputDialog(message);
            if (input == null) {
                return -1;
            }
            number = stringToDouble(input);
        }

        return number;
    }

    private static double stringToDouble(String input) {

        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input !\nPress ENTER to try again !");
            return -1;
        }
    }

    public static String inputString(String message) {

        String input = "";

        do {
            input = JOptionPane.showInputDialog(message);
            if (input == null) {
                return null;
            }
            if (input.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty !\n"
                        + "Press ENTER to try again !");
            }
        } while (input.equalsIgnoreCase(""));

        return input;

    }

}
