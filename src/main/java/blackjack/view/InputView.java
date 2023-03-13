package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NEW_LINE = "\n";
    private static final String INPUT_PLAYERS_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_MONEY_MESSAGE = "%s의 배팅 금액은?";
    private static final String INPUT_ORDER_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String COMMA = ",";
    private static final int LIMIT = -1;

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayers() {
        System.out.println(NEW_LINE + INPUT_PLAYERS_MESSAGE);
        final String input = scanner.nextLine();

        return parseNames(input);
    }

    private List<String> parseNames(final String input) {
        return Arrays.stream(input.split(COMMA, LIMIT))
                .map(String::strip)
                .collect(Collectors.toUnmodifiableList());
    }

    public String readBettingMoney(final String name) {
        System.out.println(NEW_LINE + String.format(INPUT_BETTING_MONEY_MESSAGE, name));
        return scanner.nextLine();
    }

    public String readOrderCard(final String name) {
        System.out.println(NEW_LINE + String.format(INPUT_ORDER_CARD_MESSAGE, name));

        return scanner.nextLine();
    }
}
