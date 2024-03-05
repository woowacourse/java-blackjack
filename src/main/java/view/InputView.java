package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String input = scanner.nextLine();
        String[] names = input.split(",");
        return Arrays.stream(names).toList();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
