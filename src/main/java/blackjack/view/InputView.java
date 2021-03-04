package blackjack.view;

import java.util.Scanner;

public class InputView {
    public static final String WRONG_INPUT_ERROR_MESSAGE = "유효한 대답이 아닙니다.";
    public static final String OK = "y";
    private static final String NO = "n";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputName() {
        return scanner.nextLine();
    }

    public static boolean inputAnswer() {
        String answer = scanner.nextLine();
        if (!answer.equals(OK) && !answer.equals(NO)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MESSAGE);
        }
        return answer.equals(OK);
    }
}
