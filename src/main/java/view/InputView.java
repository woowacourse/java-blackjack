package view;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public String askUserInputWithMessage(String message) {
        System.out.println(message);
        return askUserInput();
    }

    public String askUserInput() {
        return scanner.nextLine();
    }
}
