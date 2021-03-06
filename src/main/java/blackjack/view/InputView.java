package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BLANK = " ";
    private static final String REPLACEMENT = "";

    public static String inputString() {
        return SCANNER.nextLine().trim();
    }
}
