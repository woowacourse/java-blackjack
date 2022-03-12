package blackjack.view;

import blackjack.domain.game.Players;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String CARDS_FORMAT = "%s카드: %s";
    private static final String DELIMITER = Players.DELIMITER + " ";
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

    public static void printDrawInitCards(final String dealerName, final List<String> playerNames) {
        printNewLine();
        System.out.printf("%s와 %s에게 2장씩 나누었습니다.", dealerName, String.join(DELIMITER, playerNames));
        printNewLine();
    }

    public static void printCards(final String name, final List<String> playingCards) {
        System.out.printf(CARDS_FORMAT, name, String.join(DELIMITER, playingCards));
        printNewLine();
    }

    public static void printDrawInstruction(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        printNewLine();
    }

    public static void printDrawDealer(final String name, final int receivedMaximum) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.", name, receivedMaximum);
        printNewLine();
    }

    public static void printScore(final String name, final List<String> playingCards, final int total) {
        System.out.printf(CARDS_FORMAT + " - 결과: %d", name, String.join(DELIMITER, playingCards), total);
        printNewLine();
    }

    public static void printResultTitle() {
        System.out.println("## 최종 승패");
    }

    public static void printDealerResult(final String name, final int countOfWinning, final int countOfLosing) {
        System.out.printf("%s: %d%s %d%s", name, countOfWinning, WIN, countOfLosing, LOSE);
        printNewLine();
    }

    public static void printPlayerResult(final String name, final boolean isWinning) {
        System.out.printf("%s: %s", name, getResult(isWinning));
        printNewLine();
    }

    private static String getResult(final boolean isWinning) {
        if (isWinning) {
            return WIN;
        }
        return LOSE;
    }
}
