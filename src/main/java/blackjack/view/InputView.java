package blackjack.view;

import static blackjack.view.InputView.MoreDraw.NO;
import static blackjack.view.InputView.MoreDraw.YES;
import static java.text.MessageFormat.format;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();

        final List<String> names = Parser.split(input, ",");
        return Parser.trim(names);
    }

    public boolean readMoreDraw(final String name) {
        System.out.println(format("{0}은(는) 한장의 카드를 더 받겠습니까?(예는 {1}, 아니오는 {2})", name, YES, NO));
        final String input = scanner.nextLine();

        return MoreDraw.from(input).getState();
    }

    public int readBetting(final String name) {
        System.out.println(LINE_SEPARATOR + format("{0}의 배팅 금액은?", name));
        final String input = scanner.nextLine();

        return Integer.parseInt(input);
    }

    enum MoreDraw {

        YES("y", true),
        NO("n", false);

        private final String symbol;
        private final boolean state;

        MoreDraw(final String symbol, final boolean state) {
            this.symbol = symbol;
            this.state = state;
        }

        public static MoreDraw from(final String symbol) {
            return Arrays.stream(values())
                    .filter(moreDraw -> moreDraw.symbol.equals(symbol))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
        }

        public boolean getState() {
            return state;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
}
