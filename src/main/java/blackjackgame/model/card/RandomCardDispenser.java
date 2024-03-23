package blackjackgame.model.card;

import java.util.Random;

public class RandomCardDispenser implements CardDispenser {

    private static final RandomCardDispenser INSTANCE = new RandomCardDispenser();
    private static final Random random = new Random();

    private RandomCardDispenser() {
    }

    public static RandomCardDispenser getInstance() {
        return INSTANCE;
    }

    public CardNumber generateCardNumber() {
        return CardNumber.values()[(random.nextInt(CardNumber.values().length))];
    }

    public CardShape generateCardShape() {
        return CardShape.values()[(random.nextInt(CardShape.values().length))];
    }
}
