package domain;

import domain.participant.Participant;

/**
 * 승패 판단과 수익 계산을 관리하는 클래스
 */
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
