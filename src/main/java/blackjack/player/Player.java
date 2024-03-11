package blackjack.player;

import blackjack.card.Card;
import blackjack.card.Deck;
import java.util.List;

public class Player {

    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;
    protected final Hand hand;

    Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    Player(String name) {
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
        return hand.calculateScore() < BLACKJACK_MAX_SCORE;
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
