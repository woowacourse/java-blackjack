package model.participant;

import java.util.List;
import model.Card;

public final class Player extends Participant {
    private final int amount;

    private Player(String name, int amount) {
        super(name);
        this.amount = amount;
    }

    public static Player of(String input, int betAmount) {
        return new Player(input, betAmount);
    }

    @Override
    public List<String> open() {
        return hands.stream()
                .map(Card::toString)
                .toList();
    }
}
