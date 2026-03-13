package model.participant;

import java.util.List;
import model.Card;
import model.vo.BetAmount;

public final class Player extends Participant {
    private final BetAmount betAmount;

    private Player(String name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public static Player of(String input, BetAmount betAmount) {
        return new Player(input, betAmount);
    }

    @Override
    public List<String> open() {
        return hands.open();
    }

    public int getBetAmount() {
        return betAmount.getAmount();
    }
}
