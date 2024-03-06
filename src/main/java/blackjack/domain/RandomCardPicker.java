package blackjack.domain;

import java.util.List;
import java.util.Random;

public class RandomCardPicker implements CardPicker {

    private static final Random RANDOM = new Random();
    @Override
    public Card pick(final List<Card> cards) {
        return cards.get(RANDOM.nextInt(cards.size()));
    }
}
