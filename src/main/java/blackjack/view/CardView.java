package blackjack.view;

import blackjack.domain.card.Card;
import java.util.List;

public record CardView() {

    public static String makeCardOutput(final Card card) {
        return RankView.toSymbol(card.getRank()) + SuitView.toSuitView(card.getSuit());
    }

    public static List<String> makeCardOutput(final List<Card> cards) {
        return cards.stream()
                .map(CardView::makeCardOutput)
                .toList();
    }
}
