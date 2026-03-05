package blackjack.model;

import java.util.List;
import java.util.Random;

public class RandomPickStrategy implements PickStrategy {

    @Override
    public Card pick(List<Card> cards) {
        Random random = new Random();
        int randomIndex = random.nextInt(0, cards.size());

        return cards.remove(randomIndex);
    }
}
