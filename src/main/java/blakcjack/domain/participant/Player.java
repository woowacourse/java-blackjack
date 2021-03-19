package blakcjack.domain.participant;

import blakcjack.domain.money.BettingMoney;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.score.Score;

import java.util.Objects;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(final String name, final double bettingMoney) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public Outcome decideOutcome(final Dealer dealer) {
        if (hasAnyBust(dealer)) {
            return judgeOutcomeByBust();
        }
        return judgeOutcomeByScore(dealer);
    }

    private boolean hasAnyBust(final Dealer dealer) {
        return isBust() || dealer.isBust();
    }

    private Outcome judgeOutcomeByBust() {
        if (isBust()) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome judgeOutcomeByScore(final Dealer dealer) {
        final Score myScore = cards.calculateScore();
        final Score dealerScore = dealer.cards.calculateScore();

        if (myScore.isHigherThan(dealerScore)) {
            return Outcome.WIN;
        }
        if (myScore.isLowerThan(dealerScore)) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    public int calculateEarning(final Dealer dealer) {
        final Outcome outcome = decideOutcome(dealer);
        return bettingMoney.toEarning(outcome, isBlackJack());
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.PLAYER;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Player player = (Player) o;
        return Objects.equals(bettingMoney, player.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bettingMoney);
    }
}
