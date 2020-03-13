package model.user;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardHand;

public class BlackJackPerson {
    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final int BLACK_JACK_COUNT = 21;

    protected final String name;
    protected final CardHand cardHand;

    public BlackJackPerson(CardHand cardHand) {
        this(DEALER_NAME, cardHand);
    }

    public BlackJackPerson(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand) {
            cardNames.add(card.toString());
        }
        return String.join(DELIMITER, cardNames);
    };

    public void drawCard(CardHand cardHand) {
        for (Card drawCard : cardHand) {
            this.cardHand.addCard(drawCard);
        }
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_COUNT;
    }

    public int getScore() {
        return cardHand.calculateScore();
    }

    public String getName() {
        return name;
    }
}
