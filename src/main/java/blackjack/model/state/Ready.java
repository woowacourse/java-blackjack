package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public final class Ready implements State {

    private final Cards cards;

    public Ready() {
        this.cards = new Cards();
    }

    private Ready(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(final Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        if (cards.canHit()) {
            return new Hit(cards);
        }
        return new Ready(cards);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public List<String> getCards() {
        return cards.getCardNames();
    }

    @Override
    public int getScore() {
        return cards.sumScore();
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 현재 Ready이기 때문에 Stay 할 수 없습니다.");
    }
}
