package domain.player;

import domain.card.Card;
import domain.card.Hand;
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
        return getTotalScore() > BLACK_JACK;
    }

    public boolean isHittable() {
        return getTotalScore() < BLACK_JACK;
    }

    public int getTotalScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.name();
    }

}
