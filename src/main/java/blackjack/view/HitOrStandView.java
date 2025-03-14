package blackjack.view;

import static blackjack.view.Constant.LINE;

import java.util.Scanner;

public class HitOrStandView implements InputView {

    private static final String INPUT_WANT_HIT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner scanner = new Scanner(System.in);

    private final String value;

    public HitOrStandView(final String value) {
        this.value = value;
    }

    @Override
    public String read() {
        System.out.println(String.format(LINE + INPUT_WANT_HIT, value));
        return scanner.nextLine();
    }
}
