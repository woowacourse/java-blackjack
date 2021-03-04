package blackjack.view;

import blackjack.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";

    public static void distributeMessage(final String players) {
        System.out.printf(NEWLINE + "딜러와 %s에게 2장의 카드를 나누어주었습니다." + NEWLINE, players);
    }

    public static void showDealerCard(final String name, final Card card) {
        System.out.printf("%s: %s", name, card.getCard() + NEWLINE);
    }

    public static void showPlayerCard(final String name, final List<Card> cards) {
        final String cardStatus = cards.stream()
                        .map(Card::getCard)
                        .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", name, cardStatus + NEWLINE);
    }

    public static void showCardResult(final String name, final List<Card> cards, final int result) {
        final String cardStatus = cards.stream()
                .map(Card::getCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s - 결과: %d" + NEWLINE, name, cardStatus, result);
    }

    public static void showGameResult(String name, int winCount, int loseCount) {
        System.out.println(NEWLINE + "## 최종 승패");
        System.out.printf("%s: %d승 %d패" + NEWLINE, name, winCount, loseCount);
    }

    public static void showPlayerGameResult(String name, boolean winner) {
        System.out.println(name + winningMark(winner));
    }

    private static String winningMark(boolean winner) {
        if (winner) {
            return ": 승";
        }
        return ": 패";
    }

    public static void bustMessage() {
        System.out.println(BUST_MESSAGE);
    }
}
