package model.participant;

import java.util.List;
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

    public int getBetAmount() {
        return betAmount.getAmount();
    }

    @Override
    public List<String> open() {
        return hands.open();
    }

    @Override
    public List<String> openAll() {
        return hands.open();
    }

    @Override
    public int calculateRevenue(int dealerScore, boolean dealerBust, boolean dealerBlackJack) {
        int playerScore = calculateScore();
        if (dealerBust && isBust()) {
            return -getBetAmount();
        }
        if (dealerBust && !isBust()) {
            return getBetAmount();
        }
        if (dealerBlackJack && isBlackJack() || playerScore == dealerScore) {
            return 0;
        }
        if (isBlackJack() && !dealerBlackJack) {
            return (int) (getBetAmount() * 1.5);
        }
        if (dealerBlackJack && !isBlackJack()) {
            return -getBetAmount();
        }
        if (isBust()) {
            return -getBetAmount();
        }
        if (playerScore > dealerScore) {
            return getBetAmount();
        }

        return -getBetAmount();
    }
}
