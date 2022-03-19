package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public class Hit implements State {

    private final Cards cards;

    protected Hit(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isMaxScore()) {
            return new Stay(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isHit() {
        return true;
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
        return new Stay(cards);
    }
}
