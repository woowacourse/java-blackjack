package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class DealerRunning extends Running {

    private static final int HIT_STANDARD_SCORE = 17;

    DealerRunning(Cards cards) {
        super(cards);
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        if (shouldHit(cards)) {
            return new DealerRunning(cards);
        }
        return new Stand(cards);
    }

    @Override
    public State hit(Card card) {
        Cards cards = this.cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (shouldHit(cards)) {
            return new DealerRunning(cards);
        }
        return new Stand(cards);
    }

    private static boolean shouldHit(Cards cards) {
        return cards.calculateScore().isLessThan(HIT_STANDARD_SCORE);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("[ERROR] 딜러는 스스로 Stand할 수 없습니다.");
    }
}
