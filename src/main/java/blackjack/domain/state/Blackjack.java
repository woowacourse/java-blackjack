package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends TurnFinished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public State judge(State turnFinished) {
        if (turnFinished.isBlackjack()) {
            return new Draw(getCards());
        }
        return new Win(getCards());
    }
}
