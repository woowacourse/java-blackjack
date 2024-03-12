package domain.card;

import java.util.List;
import java.util.Random;

public final class RandomCardSelectStrategy implements CardSelectStrategy {

    public static final RandomCardSelectStrategy INSTANCE = new RandomCardSelectStrategy();
    private final Random random = new Random();

    @Override
    public Card select(List<Card> cards) {
        int idx = random.nextInt(cards.size());
        return cards.get(idx);
    }
}
