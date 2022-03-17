package blackjack.domain.participant.state.finished;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.PlayState;
import blackjack.domain.result.MatchStatus;

public abstract class FinishedState extends PlayState {

    protected FinishedState(PlayState state) {
        super(state);
    }

    @Override
    public PlayState betAmount(int amount) {
        throw new IllegalStateException("이미 베팅이 완료되었습니다.");
    }

    @Override
    public PlayState drawCard(Deck deck) {
        throw new IllegalStateException("턴이 이미 종료되었습니다.");
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return false;
    }

    public abstract MatchStatus judgeMatchStatus(final FinishedState state);

    public abstract boolean isBlackjack();

    public abstract boolean isBust();

}
