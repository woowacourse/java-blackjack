package model.participant;

import java.util.List;
import model.card.Card;

public class Dealer extends Participant {
    public static final String NAME = "딜러";
    public static final int DRAW_THRESHOLD = 16;

    private boolean firstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(NAME);
    }

    @Override
    public List<Card> open() {
        if (firstTurn) {
            firstTurn = false;
            return List.of(hand.getFirst());
        }

        return hand.asList();
    }

    public boolean needDraw() {
        return this.calculateScore() <= DRAW_THRESHOLD;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + getName() + '\'' +
                "firstTurn=" + firstTurn +
                ", hand=" + hand +
                '}';
    }
}
