package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Hit implements State {

    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        Cards newCards = cards.add(card);

        if (newCards.sum() > 21) {
            return new Bust(newCards);
        }

        return new Hit(cards.add(card));
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
