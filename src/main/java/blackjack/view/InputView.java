package blackjack.view;


import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String CHANGE_LINE = "\n";
    private static final String INPUT_PLAYERS_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ORDER_CARD_MESSAGE = CHANGE_LINE + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String TARGET_SPACE = " ";
    private static final String REPLACEMENT = "";
    private static final String INPUT_PLAYER_DELIMITER = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> inputPlayers() {
        System.out.println(INPUT_PLAYERS_MESSAGE);
        return List.of(scanner.nextLine().replace(TARGET_SPACE, REPLACEMENT).split(INPUT_PLAYER_DELIMITER));
    }

    public boolean inputOrderCard(final String name) {
        System.out.println(String.format(INPUT_ORDER_CARD_MESSAGE, name));
        String input = scanner.nextLine();
        validate(input);
        return input.equals(YES);
    }

    public void validate(String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException();
        }
    }
}
