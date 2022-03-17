package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackRule.BLACKJACK_SCORE;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.finished.BustState;
import blackjack.domain.participant.state.finished.StandState;

public class HitState extends PlayState {

    public HitState(final PlayState state) {
        super(state);
    }

    @Override
    public PlayState betAmount(final int amount) {
        throw new IllegalStateException("이미 베팅이 완료되었습니다.");
    }

    @Override
    public PlayState drawCard(final Deck deck) {
        this.addCard(deck);
        final int score = this.getScore();
        if (BLACKJACK_SCORE.isUnderThan(score)) {
            return new BustState(this);
        }
        if (BLACKJACK_SCORE.equalsScore(score)) {
            return new StandState(this);
        }
        return this;
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return true;
    }

    @Override
    public String toString() {
        return "HitState{}";
    }

}
