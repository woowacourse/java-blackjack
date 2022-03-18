package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class JustBlackjackDeck implements Deck {

    private final List<Card> cards = new ArrayList<>();

    @Override
    public Card pick() {
        if (cards.isEmpty()) {
            init();
        }
        return cards.remove(0);
    }

    private void init() {
        cards.addAll(List.of(
                Card.of(CardNumber.ACE, Type.SPADE),
                Card.of(CardNumber.TEN, Type.SPADE)
        ));
    }
}
