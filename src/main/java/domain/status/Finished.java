package domain.status;

import domain.card.Card;
import domain.participant.HandCards;

import java.util.List;

public abstract class Finished extends Status {
    public Finished(HandCards cards) {
        super(cards);
    }

    @Override
    public Status drawInitialCards(List<Card> cards) {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
    @Override
    public Status draw(Card card) {
        throw new IllegalStateException("이미 턴이 끝난 상태에서는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public Status stay() {
        throw new IllegalStateException("이미 턴이 끝난 상태입니다.");
    }
}
