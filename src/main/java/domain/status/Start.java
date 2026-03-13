package domain.status;

import domain.card.Card;
import domain.participant.HandCards;

import java.util.List;

public final class Start extends Status{

    public Start(final HandCards cards) {
        super(cards);
    }

    @Override
    public Status drawInitialCards(List<Card> initCards) {
        cards.receiveInitialCards(initCards);
        if(cards.isBlackJack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public Status draw(Card card) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public Status stay() {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningsRate(Status dealerStatus) {
        throw new IllegalStateException("게임이 끝나지 않아 수익률을 계산할 수 없습니다.");
    }
}
