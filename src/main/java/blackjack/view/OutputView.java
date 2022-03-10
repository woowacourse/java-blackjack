package blackjack.view;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DELIMITER = ", ";
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
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printReceiveInitCardMessage(final String dealerName, final List<String> playerNames) {
        printNewLine();
        System.out.printf("%s와 %s에게 2장씩 나누었습니다.", dealerName, String.join(DELIMITER, playerNames));
        printNewLine();
    }

    public static void printCard(final String name, final List<String> cards) {
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cards));
    }

    public static void printTakeCardInstruction(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        printNewLine();
    }

    public static void printTakeDealerCardsMessage(final String name, final int maximum) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.", name, maximum);
        printNewLine();
    }

    public static void printResult(final String name, final List<String> cards, final int total) {
        printCard(name, cards);
        System.out.printf(" - 결과: %d", total);
    }

    public static void printWinnerTitle() {
        System.out.println("## 최종 승패");
    }

    public static void printDealerScore(final String name, final int winPlayersCount, final int losePlayersCount) {
        System.out.printf("%s: %d%s %d%s", name, winPlayersCount, WIN, losePlayersCount, LOSE);
        printNewLine();
    }

    public static void printPlayerScore(final String name, final boolean result) {
        System.out.printf("%s: %s", name, getResult(result));
        printNewLine();
    }

    private static String getResult(final boolean result) {
        if (result) {
            return WIN;
        }
        return LOSE;
    }
}
