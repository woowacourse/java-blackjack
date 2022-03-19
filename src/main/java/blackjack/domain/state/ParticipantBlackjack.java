package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.PlayerCards;

public class ParticipantBlackjack extends Blackjack {

    ParticipantBlackjack(PlayerCards cards) {
        super(cards);
    }

    @Override
    public Profit profit(Outcome outcome, BetMoney money) {
        if (outcome.equals(Outcome.DRAW)) {
            return money.getProfit(DRAW_RATE);
        }
        return money.getProfit(BLACKJACK_WIN_RATE);
    }
}
