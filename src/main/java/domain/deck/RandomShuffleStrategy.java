package domain.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomShuffleStrategy implements ShuffleStrategy {

    private final Random random = new Random();

    @Override
    public List<Card> shuffle(final List<Card> cards) {
        final List<Card> cardGroup = new ArrayList<>(cards);
        Collections.shuffle(cardGroup, random);
        return cardGroup;
    }
}
