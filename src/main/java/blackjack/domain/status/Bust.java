package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Bust implements Status{

    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Status draw(Card card) {
        throw new IllegalStateException("Bust 상태에선 카드를 받을 수 없습니다.");
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
