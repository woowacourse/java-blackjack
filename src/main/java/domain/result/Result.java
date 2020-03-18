package domain.result;

import domain.money.Money;
import domain.user.Dealer;
import domain.user.Player;

public enum Result {

    PLAYER_WIN_WITHOUT_BLACKJACk(1, (money -> money.multiply(1))),
    PLAYER_WIN_WITH_BLACKJACK(1.5, money -> money.multiply(1.5)),
    DRAW(0, money -> money.multiply(0)),
    DEALER_WIN(1, money -> money.multiply(1));

    private final double profitRate;
    private final ProfitCalculator calculator;

    Result(double profitRate, ProfitCalculator calculator) {
        this.profitRate = profitRate;
        this.calculator = calculator;
    }

    static Result judge(Player player, Dealer dealer) {
        // judge bust
        if (player.isBust()) {
            return DEALER_WIN;
        }

        if (dealer.isBust()) {
            if (player.isBlackjack()) {
                return PLAYER_WIN_WITH_BLACKJACK;
            }

            return PLAYER_WIN_WITHOUT_BLACKJACk;
        }

        // judge blackjack
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }

        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return PLAYER_WIN_WITH_BLACKJACK;
        }


        //judge score
        return matchScore(player, dealer);
    }

//    Money calculateProfit(Money money) {
//        return calculator.calculate(money);
//    }

    private static boolean bustExists(Player player, Dealer dealer) {
        return player.isBlackjack() || dealer.isBust();
    }

    private static Result matchScore(Player player, Dealer dealer) {
        int scoreOfPlayer = player.calculateScore();
        int scoreOfDealer = dealer.calculateScore();
        if (scoreOfPlayer < scoreOfDealer) {
            return DEALER_WIN;
        }

        if (scoreOfPlayer == scoreOfDealer) {
            return DRAW;
        }
        return PLAYER_WIN_WITHOUT_BLACKJACk;
    }

    private interface ProfitCalculator {
        Money calculate(Money money);
    }
}
