package player;

import card.Deck;
import card.Hand;
import game.BlackJackGame;

public class Player {

    private final Name name;
    protected final Hand hand;

    Player(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public boolean hasDrawableScore() {
        return hand.calculateScore() < BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
