package domain.card.shufflestrategy;

import domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardShufflerStub implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        long fixedSeed = 42L;
        Collections.shuffle(cards, new Random(fixedSeed));
    }
}
