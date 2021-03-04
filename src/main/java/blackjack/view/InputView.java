package blackjack.view;

import java.util.Scanner;

public class InputView {
    public static final String WRONG_INPUT_ERROR_MESSAGE = "유효한 대답이 아닙니다.";
    public static final String NOT_DRAW_CARD = "n";
    private static final String DRAW_CARD = "y";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String inputName() {
        return scanner.nextLine();
    }

    public static String inputAnswer() {
        String answer = scanner.nextLine();
        if (!answer.equals(DRAW_CARD) && !answer.equals(NOT_DRAW_CARD)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MESSAGE);
        }
        return answer;
    }
}
