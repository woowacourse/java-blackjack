package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackCardRule.INITIALLY_DISTRIBUTED_CARD_COUNT;
import static blackjack.domain.BlackjackScoreRule.BLACKJACK_SCORE;
import static blackjack.domain.BlackjackScoreRule.ENABLE_MAXIMUM_SCORE_UNDER_BUST;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHands;
import blackjack.domain.card.ScoreCalculator;

public final class HitState extends PlayState {

    private final CardHands cards;

    HitState(final List<Card> cards) {
        validateCardSizeIsEnough(cards);
        validateCardScoreIsCompatible(cards);
        this.cards = new CardHands(cards);
    }

    static HitState from(final List<Card> cards) {
        return new HitState(cards);
    }

    static HitState of(final Card... cards) {
        return from(List.of(cards));
    }

    @Override
    public State drawCard(final Card card) {
        cards.addCard(card);
        return considerStateByScore();
    }

    private State considerStateByScore() {
        final int score = this.getScore();
        if (ENABLE_MAXIMUM_SCORE_UNDER_BUST.isUnderThan(score)) {
            return BustState.from(this.getCards());
        }
        if (BLACKJACK_SCORE.isEquals(score)) {
            return StandState.from(this.getCards());
        }
        return this;
    }

    @Override
    public State stay() {
        return StandState.from(this.getCards());
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return true;
    }

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    private static void validateCardSizeIsEnough(final List<Card> cards) {
        if (INITIALLY_DISTRIBUTED_CARD_COUNT.isOverThan(cards.size())) {
            throw new IllegalArgumentException("카드는 2장 이상이어야 합니다.");
        }
    }

    private static void validateCardScoreIsCompatible(final List<Card> cards) {
        final int score = ScoreCalculator.calculateScore(cards);
        if (ENABLE_MAXIMUM_SCORE_UNDER_BUST.isNotOverThan(score)) {
            throw new IllegalArgumentException("합계가 21 이상이므로 Hit 상태가 될 수 없습니다.");
        }
    }

}
