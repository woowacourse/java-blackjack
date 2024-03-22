package domain.participant.player;

import static domain.card.Hand.BLACKJACK_SCORE;
import static domain.result.ProfitRate.BLACKJACK;
import static domain.result.ProfitRate.LOSE;
import static domain.result.ProfitRate.PUSH;
import static domain.result.ProfitRate.WIN;

import domain.participant.Participant;
import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;
import domain.participant.dealer.Dealer;
import domain.result.Profit;
import domain.result.ProfitRate;

public class Player extends Participant {

    private final Bet bet;

    public Player(final Name name, final Bet bet) {
        super(name);
        this.bet = bet;
    }

    @Override
    public boolean canHit() {
        return score() < BLACKJACK_SCORE;
    }

    public Profit profitAgainst(final Dealer dealer) {
        return Profit.of(bet, profitRateAgainst(dealer));
    }

    private ProfitRate profitRateAgainst(final Participant opponent) {
        if (isPush(opponent)) {
            return PUSH;
        }
        if (isBlackjack()) {
            return BLACKJACK;
        }
        if (isLose(opponent)) {
            return LOSE;
        }
        return WIN;
    }

    private boolean isPush(final Participant opponent) {
        return isBothBust(opponent) || score() == opponent.score();
    }

    private boolean isLose(final Participant opponent) {
        if (opponent.isBust()) {
            return false;
        }
        return isBust() || (score() < opponent.score());
    }

    private boolean isBothBust(final Participant opponent) {
        return isBust() && opponent.isBust();
    }
}
