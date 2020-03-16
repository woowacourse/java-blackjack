package model;

import java.util.ArrayList;
import java.util.List;

import static controller.BlackJackGame.*;

public abstract class User {
    public static final String DELIMITER = ", ";

    protected final String name;
    protected final CardHand cardHand;

    public User(String name, Deck deck, int initialDrawCount) {
        this.name = name;
        this.cardHand = deck.draw(initialDrawCount);
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

    public void drawCard(Deck deck, int drawCount) {
        for (Card drawCard : deck.draw(drawCount)) {
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
