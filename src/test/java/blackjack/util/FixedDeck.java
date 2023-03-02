package blackjack.util;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import java.util.ArrayList;
import java.util.List;

public class FixedDeck implements Deck {

    private final List<Card> cards;
    private int index = 0;

    public FixedDeck(final List<Rank> ranks) {
        final List<Card> result = new ArrayList<>();
        for (Rank rank : ranks) {
            result.add(new Card(rank, Shape.DIAMOND));
        }
        this.cards = result;
    }

    @Override
    public Card draw() {
        return cards.get(index++);
    }
}
