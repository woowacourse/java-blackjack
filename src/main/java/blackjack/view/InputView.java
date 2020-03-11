package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";

    public static List<String> inputUserNames() {
        OutputView.printInputUserNamesGuideMessage();
        return Arrays.stream(SCANNER.nextLine().split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
