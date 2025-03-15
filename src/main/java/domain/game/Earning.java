package domain.game;

import domain.participant.Player;

public class Earning {
    private final double amount;

    private Earning(double amount) {
        this.amount = amount;
    }

    public static Earning calculate(GameResult gameResult, Player player, Bet bet) {
        if (gameResult == GameResult.LOSE) {
            return new Earning(bet.getLossAmount());
        }
        if (gameResult == GameResult.WIN && player.isBlackJack()) {
            return new Earning(bet.getBlackjackWinAmount());
        }
        if (gameResult == GameResult.WIN || gameResult == GameResult.PUSH) {
            return new Earning(bet.getAmount());
        }
        return null;
    }

    public double getAmount() {
        return amount;
    }
}
