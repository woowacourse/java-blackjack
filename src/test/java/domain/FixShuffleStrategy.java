package domain;

import domain.card.Card;
import domain.card.ShuffleStrategy;
import java.util.List;

public class FixShuffleStrategy implements ShuffleStrategy {

    private final List<Card> fixCard;

    public FixShuffleStrategy(List<Card> fixCard) {
        this.fixCard = fixCard;
    }

    @Override
    public void shuffle(List<Card> cards) {
        cards.clear();
        cards.addAll(fixCard);
    }
}
