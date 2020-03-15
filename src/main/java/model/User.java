package model;

import java.util.ArrayList;
import java.util.List;

import static controller.BlackJackGame.BLACK_JACK_COUNT;
import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public abstract class User {
    public static final String DELIMITER = ", ";
    public static final String DEALER_NAME = "딜러";

    protected final String name;
    protected final CardHand cardHand;

    public User(Deck deck) {
        this(DEALER_NAME, deck);
    }

    public User(String name, Deck deck) {
        this.name = name;
        this.cardHand = deck.draw(INITIAL_DRAW_COUNT);
    }

    public static int compare(final User dealer, final User player) {
        return Integer.compare(dealer.getScore(), player.getScore());
    }

    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand) {
            cardNames.add(card.toString());
        }
        return String.join(DELIMITER, cardNames);
    }

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
