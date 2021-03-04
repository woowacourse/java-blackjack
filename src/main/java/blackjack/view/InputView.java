package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String YES = "y";
    private static final String NO = "n";


    private InputView() {

    }

    public static String inputName() {
        return scanner.nextLine();
    }

    public static String inputAnswer() {
        String answer = scanner.nextLine();
        if (!answer.equals(YES) && !answer.equals(NO)) {
            OutputView.printError("유효한 대답이 아닙니다.");
            return inputAnswer();
        }
        return answer;
    }
}
