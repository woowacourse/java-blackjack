package game;

import java.util.function.Function;

public enum GameResult {

    WIN(betting -> betting),
    LOSE(betting -> (-1) * betting),
    DRAW(betting -> 0),
    BLACKJACK(betting -> (int) (1.5 * betting));

    private final Function<Integer, Integer> evaluateStrategy;

    GameResult(Function<Integer, Integer> evaluateStrategy) {
        this.evaluateStrategy = evaluateStrategy;
    }

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return ifNotBlackjack(dealer, player);
    }

    private static GameResult ifNotBlackjack(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return ifNotBust(dealer, player);
    }

    private static GameResult ifNotBust(Dealer dealer, Player player) {
        int totalOfPlayer = player.calculateTotalPoints();
        int totalOfDealer = dealer.calculateTotalPoints();
        if (totalOfPlayer < totalOfDealer) {
            return LOSE;
        }
        if (totalOfPlayer > totalOfDealer) {
            return WIN;
        }
        return DRAW;
    }

    public int evaluate(int betting) {
        return evaluateStrategy.apply(betting);
    }
    
}
