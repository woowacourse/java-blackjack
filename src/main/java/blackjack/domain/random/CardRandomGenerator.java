package blackjack.domain.random;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardRandomGenerator implements CardGenerator {

    @Override
    public Card pickRandomCard() {
        final List<Card> cards = new ArrayList<>(DECK);
        Collections.shuffle(cards);
        return cards.getFirst();
    }
}
