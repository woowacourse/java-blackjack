package domain.player;

import domain.deck.Card;
import java.util.List;

public class Player {
    private final Name name;
    private final Hand hand;
    private final Batting batting;

    public Player(final Name name, final Batting batting) {
        this.name = name;
        this.hand = new Hand();
        this.batting = batting;
    }

    public void drawCard(final Card card) {
        hand.addCard(card);
    }

    public List<Card> cards() {
        return hand.cards();
    }

    public Name getName() {
        return name;
    }

    public String name() {
        return name.getName();
    }

    public int score() {
        return hand.score();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
