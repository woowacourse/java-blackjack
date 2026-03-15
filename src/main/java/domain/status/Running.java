package domain.status;

import domain.card.Card;
import domain.participant.HandCards;

import java.util.List;

public abstract class Running extends Status {
    public Running(HandCards cards) {
        super(cards);
    }

    @Override
    public Status drawInitialCards(List<Card> cards) {
        throw new IllegalStateException("이미 게임이 진행 중입니다. 초기 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    public abstract Status draw(Card card);

    public abstract Status stay();
}
