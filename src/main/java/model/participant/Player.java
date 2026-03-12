package model.participant;

import java.util.List;
import model.Card;

public final class Player extends Participant {
    private final int betAmount;

    private Player(String name, int betAmount) {
        super(name);
        this.betAmount = betAmount;
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

    public int getBetAmount() {
        return betAmount;
    }
}
