package blackjack.model.cards;

import java.util.Random;

public enum CardShape {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    private static final Random random = new Random();

    public static CardShape generate() {
        CardShape[] cardShapes = values();
        return cardShapes[random.nextInt(cardShapes.length)];
    }
}
