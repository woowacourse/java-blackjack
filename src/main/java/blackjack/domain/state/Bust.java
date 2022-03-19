package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Bust extends TurnFinished {
    Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State judge(State turnFinished) {
        return new Lose(getCards());
    }
}
