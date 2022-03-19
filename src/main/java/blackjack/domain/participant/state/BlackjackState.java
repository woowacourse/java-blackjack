package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackScoreRule.BLACKJACK_SCORE;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.result.MatchStatus;

public final class BlackjackState extends FinishState {

    private BlackjackState(final List<Card> cards) {
        super(cards);
    }

    static BlackjackState from(final List<Card> cards) {
        return new BlackjackState(cards);
    }

    static BlackjackState of(final Card... cards) {
        return from(List.of(cards));
    }

    @Override
    public MatchStatus judgeMatchStatus(State state) {
        if (state.isBlackjack()) {
            return MatchStatus.DRAW;
        }
        return MatchStatus.BLACKJACK;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    protected void validateScoreIsCompatible(final List<Card> cards) {
        final int score = ScoreCalculator.calculateScore(cards);
        if (BLACKJACK_SCORE.isNotEquals(score)) {
            throw new IllegalArgumentException("합계가 21 이어야 Blackjack 상태가 될 수 있습니다.");
        }
    }

}
