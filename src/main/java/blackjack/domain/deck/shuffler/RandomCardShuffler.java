package blackjack.domain.deck.shuffler;

import blackjack.domain.card.Card;
import blackjack.domain.deck.CardShuffler;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
