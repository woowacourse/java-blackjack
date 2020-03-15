package model;

import static controller.BlackJackGame.BLACK_JACK_COUNT;

public abstract class User {
    protected final String name;
    protected final CardHand cardHand;

    public User(CardHand cardHand) {
        this("딜러", cardHand);
    }

    public User(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public static int compare(final User dealer, final User player) {
        return Integer.compare(dealer.getScore(), player.getScore());
    }

    public abstract String toStringCardHand();

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
