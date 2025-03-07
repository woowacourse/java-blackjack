package domain;

import java.util.Random;

public class CardRandomGenerator implements RandomGenerator<Card> {
    private static final Random RANDOM = new Random();

    public Card generate() {
        return new Card(
                CardNumberType.findByRandomIndex(createCardNumberTypeRandomIndex()),
                CardType.findByRandomIndex(createCardTypeRandomIndex())
        );
    }

    private int createCardTypeRandomIndex() {
        return RANDOM.nextInt(CardType.values().length);
    }

    private int createCardNumberTypeRandomIndex() {
        return RANDOM.nextInt(CardNumberType.values().length);
    }

}
