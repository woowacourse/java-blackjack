package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    private static final int MAX_SCORE_TO_HIT = 16;

    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        super(DEALER_NAME);
        this.cardDeck = cardDeck;
    }

    public Card dealCard() {
        return cardDeck.draw();
    }

    public List<Card> dealCard(int count) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cards.add(cardDeck.draw());
        }

        return cards;
    }

    public boolean isHittable() {
        int totalScore = getTotalScore();

        return totalScore <= MAX_SCORE_TO_HIT;
    }
}
