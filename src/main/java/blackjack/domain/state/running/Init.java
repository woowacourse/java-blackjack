package blackjack.domain.state.running;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.BlackJack;

public final class Init extends Running {

    public Init() {
        super(new HoldingCards());
    }

    Init(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public State drawCard(Deck deck) {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 2장의 카드까지만 받을 수 있습니다.");
    }

    public State initDistributed(Deck deck) {
        super.holdingCards().addCard(deck.drawCard());
        super.holdingCards().addCard(deck.drawCard());
        if (super.holdingCards().isBlackJack()) {
            return new BlackJack(super.holdingCards());
        }
        return new Hit(super.holdingCards());
    }
}
