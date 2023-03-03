package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        return List.of(scanner.nextLine().split(","));
    }

    public String readHitCommand() {
        return scanner.nextLine();
    }
}
