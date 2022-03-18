package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Running extends AbstractState {

    Running(Cards cards) {
        super(cards);
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Running(cards);
    }

    @Override
    public State hit(Card card) {
        Cards cards = this.cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Running(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
