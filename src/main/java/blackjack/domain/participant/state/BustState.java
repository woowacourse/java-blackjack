package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackScoreRule.ENABLE_MAXIMUM_SCORE_UNDER_BUST;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.result.MatchStatus;

public final class BustState extends FinishState {

    private BustState(final List<Card> cards) {
        super(cards);
    }

    static BustState from(final List<Card> cards) {
        return new BustState(cards);
    }

    static BustState of(final Card... cards) {
        return from(List.of(cards));
    }

    @Override
    public MatchStatus judgeMatchStatus(State state) {
        return MatchStatus.LOSS;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    protected void validateScoreIsCompatible(final List<Card> cards) {
        final int score = ScoreCalculator.calculateScore(cards);
        if (ENABLE_MAXIMUM_SCORE_UNDER_BUST.isNotUnderThan(score)) {
            throw new IllegalArgumentException("합계가 21 초과여야 Bust 상태가 될 수 있습니다.");
        }
    }

}
