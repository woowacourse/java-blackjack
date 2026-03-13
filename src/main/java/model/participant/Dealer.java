package model.participant;

import java.util.List;
import model.Card;

public final class Dealer extends Participant {
    private static final int FIRST_TURN_CARD_COUNT = 2;

    private Dealer(String name) {
        super(name);
    }

    public static Dealer of(String input) {
        return new Dealer(input);
    }

    @Override
    public List<String> open() {
        if (hands.size() == FIRST_TURN_CARD_COUNT) {
            return List.of(hands.getFirst().toString());
        }

        return hands.stream()
                .map(Card::toString)
                .toList();
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
