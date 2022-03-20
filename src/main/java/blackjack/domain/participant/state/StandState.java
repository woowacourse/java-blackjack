package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackCardRule.INITIALLY_DISTRIBUTED_CARD_COUNT;
import static blackjack.domain.BlackjackScoreRule.ENABLE_MAXIMUM_SCORE_UNDER_BUST;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.result.MatchStatus;

public final class StandState extends FinishState {

    private StandState(final List<Card> cards) {
        super(cards);
    }

    static StandState from(final List<Card> cards) {
        return new StandState(cards);
    }

    static StandState of(final Card... cards) {
        return from(List.of(cards));
    }

    @Override
    public MatchStatus judgeMatchStatus(FinishState otherState) {
        final int thisScore = this.getScore();
        final int otherScore = otherState.getScore();
        if (otherState.isBust() || thisScore > otherScore) {
            return MatchStatus.WIN;
        }
        if (otherState.isBlackjack() || (thisScore < otherScore)) {
            return MatchStatus.LOSS;
        }
        return MatchStatus.DRAW;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    protected void validateCardSizeIsEnough(final List<Card> cards) {
        if (INITIALLY_DISTRIBUTED_CARD_COUNT.isOverThan(cards.size())) {
            throw new IllegalArgumentException("카드는 2장 이상이어야 합니다.");
        }
    }

    @Override
    protected void validateScoreIsCompatible(final List<Card> cards) {
        final int score = ScoreCalculator.calculateScore(cards);
        if (ENABLE_MAXIMUM_SCORE_UNDER_BUST.isUnderThan(score)) {
            throw new IllegalArgumentException("합계가 21 이하여야 Stand 상태가 될 수 있습니다.");
        }
    }

}
