package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackCardRule.INITIALLY_DISTRIBUTED_CARD_COUNT;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHands;
import blackjack.domain.result.MatchStatus;

public abstract class FinishState implements State {

    private final CardHands cards;

    FinishState(final List<Card> cards) {
        validateCardSizeIsEnough(cards);
        validateScoreIsCompatible(cards);
        this.cards = new CardHands(cards);
    }

    public abstract MatchStatus judgeMatchStatus(final State state);

    @Override
    public final State drawCard(Card card) {
        throw new IllegalStateException("턴이 이미 종료되었습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("턴이 이미 종료되었습니다.");
    }

    @Override
    public final boolean isPossibleToDrawCard() {
        return false;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final int getScore() {
        return cards.calculateScore();
    }

    @Override
    public final List<Card> getCards() {
        return cards.getCards();
    }

    private void validateCardSizeIsEnough(final List<Card> cards) {
        if (INITIALLY_DISTRIBUTED_CARD_COUNT.isOverThan(cards.size())) {
            throw new IllegalArgumentException("카드는 2장 이상이어야 합니다.");
        }
    }

    protected abstract void validateScoreIsCompatible(final List<Card> cards);

}
