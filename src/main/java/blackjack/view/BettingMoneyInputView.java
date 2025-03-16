package blackjack.view;

import java.util.Scanner;

public class BettingMoneyInputView implements InputView {

    public static final String LINE = System.lineSeparator();
    private static final String INPUT_WANT_HIT = "%s의 배팅 금액은?";

    private final Scanner scanner;
    private final String value;

    public BettingMoneyInputView(final Scanner scanner, final String value) {
        this.scanner = scanner;
        this.value = value;
    }

    @Override
    public String read() {
        System.out.println(String.format(LINE + INPUT_WANT_HIT, value));
        return scanner.nextLine();
    }
}
