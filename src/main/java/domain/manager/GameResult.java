package domain.manager;

import domain.gamer.Dealer;
import domain.gamer.Player;

public enum GameResult {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profitMultiplier;

    GameResult(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public static GameResult decide(Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return decideWithScore(player, dealer);
    }

    private static GameResult decideWithScore(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public Profit calculateProfit(Profit profit) {
        return profit.multiply(profitMultiplier);
    }
}
