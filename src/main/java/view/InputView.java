package view;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public String askUserInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
