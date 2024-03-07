package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readNames() {
        return List.of(scanner.nextLine()
                .split(NAME_DELIMITER));
    }

    public static Boolean askDraw() {
        String answer = scanner.nextLine()
                .toLowerCase();
        if (ACCEPT_DRAW_MESSAGE.equals(answer)) {
            return Boolean.TRUE;
        }
        if (REJECT_DRAW_MESSAGE.equals(answer)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("대답은 " + ACCEPT_DRAW_MESSAGE + " 혹은 "
                + REJECT_DRAW_MESSAGE + "만 가능합니다");
    }
}
