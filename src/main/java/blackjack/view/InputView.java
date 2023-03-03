package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = "\\s*,\\s*";

    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> readPlayerNames() {
        final String nameInput = SCANNER.nextLine();
        return Arrays.stream(nameInput.split(DELIMITER)).collect(Collectors.toUnmodifiableList());
    }

    public String readDrawOrStay() {
        return SCANNER.nextLine();
    }

}
