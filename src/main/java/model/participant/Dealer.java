package model.participant;

import java.util.List;
import model.Card;

public final class Dealer extends Participant {
    private boolean isFirstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Dealer of(String input) {
        return new Dealer(input);
    }

    @Override
    public List<String> open() {
        if (isFirstTurn) {
            isFirstTurn = false;
            return List.of(hands.getFirst().toString());
        }
        return hands.stream()
                .map(Card::toString)
                .toList();
    }
}
