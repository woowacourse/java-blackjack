package blackjack.domain.participant.state;

import static blackjack.domain.BlackjackRule.BLACKJACK_SCORE;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.finished.BlackjackState;

public class WaitState extends PlayState {

    private WaitState(final PlayState state, final int amount) {
        super(state.getCards(), amount);
    }

    public WaitState(PlayState state) {
        super(state);
    }

    @Override
    public PlayState betAmount(int amount) {
        final WaitState state = new WaitState(this, amount);

        final int score = this.getScore();
        if (BLACKJACK_SCORE.equalsScore(score)) {
            return new BlackjackState(state);
        }
        return new HitState(state);
    }

    @Override
    public PlayState drawCard(Deck deck) {
        throw new IllegalStateException("베팅을 하지 않았습니다.");
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return true;
    }

    @Override
    public String toString() {
        return "WaitState{}";
    }

}
