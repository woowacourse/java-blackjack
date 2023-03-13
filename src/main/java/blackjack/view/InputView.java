package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String input = scanner.nextLine();
        final List<String> names = Parser.parseByDelimiter(input, ",");

        return Parser.trim(names);
    }

    public int readBettingAmount(final String name) {
        System.out.println(name + "의 배팅 금액은?");

        return Integer.parseInt(scanner.nextLine());
    }

    public boolean readDrawState(final String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        final String input = scanner.nextLine();

        return DrawState.from(input).getState();
    }

    enum DrawState {

        YES("y", true),
        NO("n", false);

        private final String symbol;
        private final boolean state;

        DrawState(final String symbol, final boolean state) {
            this.symbol = symbol;
            this.state = state;
        }

        public static DrawState from(final String symbol) {
            return Arrays.stream(values())
                    .filter(drawState -> drawState.getSymbol().equals(symbol))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
        }

        public String getSymbol() {
            return symbol;
        }

        public boolean getState() {
            return state;
        }
    }
}
