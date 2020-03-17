package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String YES_OR_NO = "[yn]";
    public static final String YES = "y";

    public static String getInput() {
        return scanner.nextLine();
    }

    public static boolean yesOrNo() {
        String answer = scanner.nextLine().toLowerCase();
        if (answer.matches(YES_OR_NO)) {
            return answer.equals(YES);
        }
        System.out.println("잘못된 값을 입력하셨습니다. y 또는 n만 가능합니다.");
        return yesOrNo();
    }
}
