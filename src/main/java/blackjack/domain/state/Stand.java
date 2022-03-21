package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stand extends TurnFinished {
    Stand(Cards cards) {
        super(cards);
    }

    @Override
    public State judge(State turnFinished) {
        if (turnFinished.isBust() || getCards().calculateScore() > turnFinished.getCards().calculateScore()) {
            return new Win(getCards());
        }
        if (turnFinished.isBlackjack() || getCards().calculateScore() < turnFinished.getCards().calculateScore()) {
            return new Lose(getCards());
        }
        return new Draw(getCards());
    }
}
