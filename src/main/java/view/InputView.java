package view;

import view.util.InputParse;
import view.util.InputValidate;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String HIT_COMMAND = "y";

    public static List<String> inputName() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InputValidate.validateInput(input);
        List<String> names = InputParse.separateBySeparator(input);
        return names;
    }

    public static boolean inputHitOrStand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InputValidate.validateChoiceInput(input);
        return HIT_COMMAND.equals(input);
    }
}
