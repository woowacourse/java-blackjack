package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class FixShuffleStrategy implements CardShuffleStrategy {
    private final List<Card> fixCards;

    public FixShuffleStrategy(List<Card> fixCards) {
        this.fixCards = fixCards;
    }

    @Override
    public List<Card> shuffle(List<Card> cards) {
        return new ArrayList<>(fixCards);
    }
}
