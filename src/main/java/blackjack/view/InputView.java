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
        return yesOrNo();
    }
}
