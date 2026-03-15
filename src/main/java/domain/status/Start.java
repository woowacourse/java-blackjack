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
}
