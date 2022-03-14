package blackjack.view;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DEAL_INIT_MESSAGE = "\n%s와 %s에게 2장씩 나누었습니다.\n";
    private static final String DELIMITER = ", ";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String TAKE_CARD_INSTRUCTION = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String DRAW_DEALER_CARD_MESSAGE = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_FORMAT = " - 결과: %d";
    private static final String WINNER_TITLE = "## 최종 승패";
    private static final String DEALER_SCORE_FORMAT = "%s: %d%s %d%s";
    private static final String PLAYER_SCORE_FORMAT = "%s: %s";
    private static final String WIN = "승";
    private static final String LOSE = "패";

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

    public static void printDealCardMessage(final String dealerName, final List<String> playerNames) {
        System.out.printf(DEAL_INIT_MESSAGE, dealerName, String.join(DELIMITER, playerNames));
    }

    public static void printCard(final String name, final List<String> cards) {
        System.out.printf(CARD_FORMAT, name, String.join(DELIMITER, cards));
    }

    public static void printTakeCardInstruction(final String name) {
        System.out.printf(TAKE_CARD_INSTRUCTION, name);
    }

    public static void printDrawDealerCardMessage(final String name, final int maximum) {
        System.out.printf(DRAW_DEALER_CARD_MESSAGE, name, maximum);
        printNewLine();
    }

    public static void printResult(final String name, final List<String> cards, final int total) {
        printCard(name, cards);
        System.out.printf(RESULT_FORMAT, total);
    }

    public static void printWinnerTitle() {
        System.out.println(WINNER_TITLE);
    }

    public static void printDealerScore(final String name, final int winPlayersCount, final int losePlayersCount) {
        System.out.printf(DEALER_SCORE_FORMAT, name, winPlayersCount, WIN, losePlayersCount, LOSE);
        printNewLine();
    }

    public static void printPlayerScore(final String name, final boolean result) {
        System.out.printf(PLAYER_SCORE_FORMAT, name, getResult(result));
        printNewLine();
    }

    private static String getResult(final boolean result) {
        if (result) {
            return WIN;
        }
        return LOSE;
    }
}
