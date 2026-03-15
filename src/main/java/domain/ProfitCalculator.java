package domain;

import domain.participant.Participant;

public class ProfitCalculator {

    public int calculateProfit(Participant player, Participant dealer, Money money) {
        if (dealer.isBlackjack() && player.isBlackjack()) { return money.getBack(); }
        if (player.isBlackjack()) { return money.earnOnePointFiveTimes(); }
        if (player.isBust()) { return money.lose(); }
        if (dealer.isBust()) { return money.earn(); }
        if (player.calculateScore() > dealer.calculateScore()) { return money.earn(); }
        if (player.calculateScore() == dealer.calculateScore()) { return money.getBack(); }
        return money.lose();
    }
}
