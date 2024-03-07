package player;

import card.Card;
import card.Deck;
import game.BlackJackGame;

import java.util.List;

public class Player {

    private final Name name;
    protected final Hand hand;

    Player(String name) {
        this(name, new Hand());
    }

    Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public void initDrawCards(Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public boolean hasDrawableScore() {
        return hand.calculateScore() < BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
