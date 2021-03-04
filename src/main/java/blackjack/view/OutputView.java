package blackjack.view;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_MARK = "[Error] ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";
    private static final String DEALER_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DISTRIBUTE_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어주었습니다.";
    private static final String DEALER_CARD_STATUS_FORMAT = "%s: %s";
    private static final String PLAYER_CARD_STATUS_FORMAT = "%s카드: %s";
    private static final String CARD_RESULT_FORMAT = "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_MESSAGE = "## 최종 승패";
    private static final String GAME_RESULT_FORMAT = "%s: %d승 %d패";
    private static final String WIN_MESSAGE = ": 승";
    private static final String LOSE_MESSAGE = ": 패";

    public static void distributeMessage(final String players) {
        System.out.printf(NEWLINE + DISTRIBUTE_MESSAGE + NEWLINE, players);
    }

    public static void showDealerCard(final String name, final Card card) {
        System.out.printf(DEALER_CARD_STATUS_FORMAT, name, card.getCard() + NEWLINE);
    }

    public static void showPlayerCard(final String name, final List<Card> cards) {
        final String cardStatus = cards.stream()
                .map(Card::getCard)
                .collect(Collectors.joining(", "));
        System.out.printf(PLAYER_CARD_STATUS_FORMAT, name, cardStatus + NEWLINE);
    }

    public static void showCardResult(final String name, final List<Card> cards, final int result) {
        final String cardStatus = cards.stream()
                .map(Card::getCard)
                .collect(Collectors.joining(", "));
        System.out.printf(CARD_RESULT_FORMAT + NEWLINE, name, cardStatus, result);
    }

    public static void showGameResult(final String name, final int winCount, final int loseCount) {
        System.out.println(NEWLINE + GAME_RESULT_MESSAGE);
        System.out.printf(GAME_RESULT_FORMAT + NEWLINE, name, winCount, loseCount);
    }

    public static void showPlayerGameResult(final String name, final boolean winner) {
        if (winner) {
            System.out.println(name + WIN_MESSAGE);
            return;
        }
        System.out.println(name + LOSE_MESSAGE);
    }

    public static void bustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public static void dealerMoreCard() {
        System.out.println(NEWLINE + DEALER_MORE_CARD_MESSAGE);
    }

    public static void getErrorMessage(final String message) {
        System.out.println(ERROR_MARK + message);
    }

    public static void displayNewLine() {
        System.out.println();
    }
}
