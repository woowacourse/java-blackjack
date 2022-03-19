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
        if (super.cards().size() == 0) {
            cards().addCard(deck.drawCard());
            return new Init(super.cards());
        }
        if (super.cards().size() == 1) {
            cards().addCard(deck.drawCard());
            return nextState();
        }
        throw new IllegalStateException("[ERROR] 초기 상태에서는 2장의 카드까지만 받을 수 있습니다.");
    }

    private State nextState() {
        if (super.cards().isBlackJack()) {
            return new BlackJack(super.cards());
        }
        return new Hit(super.cards());
    }
}
