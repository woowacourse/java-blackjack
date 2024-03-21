package blackjack.player;

import blackjack.card.Card;
import blackjack.card.Deck;
import java.util.List;

public class Player {

    private final Name name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    public Player(String name) {
        this(name, new Hand());
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public void doInitialDraw(Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public boolean hasDrawableScore() {
        return hand.calculateScore().isNotBust();
    }

    public String getName() {
        return name.getName();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Hand getHand() {
        return hand;
    }
}
