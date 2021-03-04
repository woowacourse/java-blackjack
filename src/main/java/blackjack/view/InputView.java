package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String inputName() {
        return scanner.nextLine();
    }

    public static String inputAnswer() {
        String answer = scanner.nextLine();
        if(!answer.equals("y") && !answer.equals("n")){
            throw new IllegalArgumentException("유효한 대답이 아닙니다.");
        }
        return answer;
    }
}
