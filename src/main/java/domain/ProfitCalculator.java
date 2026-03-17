package domain;

import domain.participant.Participant;

public class ProfitCalculator {
    public int calculate(Participant player, Participant dealer, Money money) {
        GameResult gameResult = judge(player, dealer);
        return gameResult.profit(money);
    }

    private GameResult judge(Participant player, Participant dealer) {
        if (dealer.isBlackjack() && player.isBlackjack()) { return GameResult.DRAW; }
        if (player.isBlackjack()) { return GameResult.BLACKJACK_WIN; }
        if (player.isBust()) { return GameResult.LOSE; }
        if (dealer.isBust()) { return GameResult.WIN; }
        if (player.calculateScore() > dealer.calculateScore()) { return GameResult.WIN; }
        if (player.calculateScore() == dealer.calculateScore()) { return GameResult.DRAW; }
        return GameResult.LOSE;
    }
}
