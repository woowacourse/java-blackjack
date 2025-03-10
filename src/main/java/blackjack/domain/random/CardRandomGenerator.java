package blackjack.domain.random;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardRandomGenerator implements CardGenerator {

    public Card pickCard() {
        final List<Card> cards = new ArrayList<>(DECKS);
        Collections.shuffle(cards);
        return cards.getFirst();
    }
}
