package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements PlayerState {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public PlayerState keepContinue(boolean input) {
        if (!input) {
            return new Stay();
        }
        if (input) {
            return this;
        }
        throw new IllegalArgumentException("옳지 않은 입력입니다.");
    }

    public PlayerState drawNewCard(Cards cards, Card card) {
        cards.addCard(card);
        if (cards.calculateIncludeAce() > 21) {
            return new Burst();
        }
        return this;
    }
}
