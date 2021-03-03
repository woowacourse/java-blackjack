package blackjack.view;

import blackjack.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");

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
}
