package domain.player;

import domain.card.Card;
import domain.card.Hand;
import domain.game.Score;
import java.util.List;

public class Player {

    public static final int BLACK_JACK = 21;

    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Player(List<Card> cards) {
        this.name = new Name("Test");
        this.hand = new Hand(cards);
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public boolean isHittable() {
        return getScore().compareTo(BLACK_JACK) < 0;
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.name();
    }

}
