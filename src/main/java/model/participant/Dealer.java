package model.participant;

import java.util.List;
import model.Card;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;

    private boolean firstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Participant of(String input) {
        return new Dealer(input);
    }

    @Override
    public List<String> open() {
        if (firstTurn) {
            firstTurn = false;
            return List.of(hands.getFirst().toString());
        }

        return hands.stream()
                .map(Card::toString)
                .toList();
    }

    public boolean needDraw() {
        return this.calculateScore() <= DRAW_THRESHOLD;
    }
}
