package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackCardRule.INITIALLY_DISTRIBUTED_CARD_COUNT;
import static blackjack.domain.BlackjackScoreRule.BLACKJACK_SCORE;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHands;

public final class InitialState extends PlayState {

    private final CardHands cards;

    private InitialState(final List<Card> cards) {
        this.cards = new CardHands(cards);
    }

    public static State initiallyDrawCards(final List<Card> cards) {
        validateInitialCardsSize(cards);
        final InitialState initialState = new InitialState(cards);
        return initialState.considerStateByScore();
    }

    public static State initiallyDrawCards(final Card... cards) {
        return initiallyDrawCards(List.of(cards));
    }

    private static void validateInitialCardsSize(final List<Card> cards) {
        if (INITIALLY_DISTRIBUTED_CARD_COUNT.isNotEquals(cards.size())) {
            throw new IllegalArgumentException("처음에 배분받는 카드는 2장이어야 합니다.");
        }
    }

    private State considerStateByScore() {
        final int score = this.getScore();
        if (BLACKJACK_SCORE.isEquals(score)) {
            return BlackjackState.from(this.getCards());
        }
        return new HitState(this.getCards());
    }

    @Override
    public PlayState drawCard(final Card card) {
        throw new IllegalStateException("카드를 뽑을 준비가 되지 않았습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("턴이 시작하지 않았습니다.");
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return false;
    }

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

}
