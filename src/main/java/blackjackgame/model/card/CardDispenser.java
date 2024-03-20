package blackjackgame.model.card;

import java.util.Random;

public class CardDispenser {

    private static final Random random = new Random();

    private CardDispenser() {
    }

    public static CardNumber generateCardNumber() {
        return CardNumber.values()[(random.nextInt(CardNumber.values().length))];
    }

    public static CardShape generateCardShape() {
        return CardShape.values()[(random.nextInt(CardShape.values().length))];
    }
}
