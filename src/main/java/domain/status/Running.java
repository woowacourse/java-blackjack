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
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public double earningsRate(Status dealerStatus) {
        throw new IllegalStateException("게임이 끝나지 않아 수익률을 계산할 수 없습니다.");
    }

    public abstract Status draw(Card card);

    public abstract Status stay();
}
