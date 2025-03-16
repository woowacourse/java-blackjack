package blackjack.view;

import java.util.Scanner;

public class HitOrStandInputView implements InputView {

    public static final String LINE = System.lineSeparator();
    private static final String INPUT_WANT_HIT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner;
    private final String value;

    public HitOrStandInputView(final Scanner scanner, final String value) {
        this.scanner = scanner;
        this.value = value;
    }

    @Override
    public String read() {
        System.out.println(String.format(LINE + INPUT_WANT_HIT, value));
        return scanner.nextLine();
    }
}
