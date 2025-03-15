package blackjack.view;

import static blackjack.view.Constant.LINE;

import java.util.Scanner;

public class BettingMoneyInputView implements InputView {

    private static final String INPUT_WANT_HIT = "%s의 배팅 금액은?";
    private static final Scanner scanner = new Scanner(System.in);

    private final String value;

    public BettingMoneyInputView(final String value) {
        this.value = value;
    }

    @Override
    public String read() {
        System.out.println(String.format(LINE + INPUT_WANT_HIT, value));
        return scanner.nextLine();
    }
}
