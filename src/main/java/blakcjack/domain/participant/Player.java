package blakcjack.domain.participant;

import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.score.Score;

public class Player extends Participant {
    public Player(final String name) {
        this(name, 0);
    }

    public Player(final String name, final double money) {
        super(name, money);
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

    @Override
    public ParticipantType getType() {
        return ParticipantType.PLAYER;
    }
}
