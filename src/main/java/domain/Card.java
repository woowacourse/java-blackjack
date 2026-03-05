package domain;

import java.util.Random;

public class Card {
    private static final int CARD_BOUND = 52;
    private final int card;

    public Card() {
        this.card = drawCard();
    }

    private int drawCard() {
        Random random = new Random();
        return random.nextInt(CARD_BOUND);
    }

    public int getCard() {
        return card;
    }

    public boolean checkAce() {
        return card % 13 == 0;
    }
}
