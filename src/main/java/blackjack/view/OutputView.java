package blackjack.view;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String GIVE_INIT_CARD_MESSAGE = "\n%s와 %s에게 2장씩 나누었습니다.\n";
    public static final String CARD_FORMAT = "%s카드: %s";
    public static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printPlayerNameInstruction() {
        System.out.println(PLAYER_NAME_MESSAGE);
    }

    public static void printReceiveInitCardMessage(final String dealerName, final List<String> playerNames) {
        System.out.printf(GIVE_INIT_CARD_MESSAGE, dealerName, String.join(DELIMITER, playerNames));
    }

    public static void printCard(final String name, final List<String> cards) {
        System.out.printf(CARD_FORMAT, name, String.join(DELIMITER, cards));
    }

    public static void printTakeCardInstruction(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
    }
}
