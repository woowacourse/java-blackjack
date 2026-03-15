package blackjack.model.cardDeck;

import blackjack.model.card.Card;
import java.util.List;
import java.util.Random;

public class RandomPickStrategy implements PickStrategy {

    private final static Random random = new Random();

    @Override
    public Card pick(List<Card> cards) {
        int randomIndex = random.nextInt(0, cards.size());
        return cards.remove(randomIndex);
    }
}
