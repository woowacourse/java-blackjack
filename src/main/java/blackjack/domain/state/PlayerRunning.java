package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class PlayerRunning extends Running {

    public PlayerRunning(Cards cards) {
        super(cards);
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new PlayerRunning(cards);
    }

    @Override
    public State hit(Card card) {
        Cards cards = this.cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new PlayerRunning(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }
}
