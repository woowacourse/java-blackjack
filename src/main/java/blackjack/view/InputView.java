package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputPlayerNames() {
        List<String> names = Arrays.stream(
                scanner.nextLine().split(",")
        ).toList();

        InputValidator.validatePlayerNames(names);

        return names;
    }

    public boolean inputMoreCard() {
        String input = scanner.nextLine();

        if("y".equals(input)){
            return true;
        }
        if("n".equals(input)) {
            return false;
        }

        throw new IllegalArgumentException("'y' 또는 'n'만 입력 가능합니다.");
    }

    public void closeScanner() {
        scanner.close();
    }
}
