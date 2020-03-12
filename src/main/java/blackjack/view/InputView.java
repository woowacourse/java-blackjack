package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }

    public static boolean yesOrNo() {
        String answer = scanner.nextLine().toLowerCase();
        if (answer.matches("[yn]")) {
            return answer.equals("y");
        }
        System.out.println("잘못된 값을 입력하셨습니다. y 또는 n만 가능합니다.");
        return yesOrNo();
    }
}
