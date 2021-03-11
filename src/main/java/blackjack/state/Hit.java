package blackjack.state;


import blackjack.domain.card.Card;

public class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);

        if (this.cards.isBust()) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }


}
