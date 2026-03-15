package domain;

import domain.card.Card;
import domain.card.ShuffleStrategy;
import java.util.ArrayList;
import java.util.List;

public class FixShuffleStrategy implements ShuffleStrategy {

    private final List<Card> fixCard;

    public FixShuffleStrategy(List<Card> fixCard) {
        this.fixCard = fixCard;
    }

    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.clear();
        newCards.addAll(fixCard);
        return newCards;
    }
}
