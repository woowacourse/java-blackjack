package blackjack.state;


import blackjack.domain.card.Card;

public class PlayerHit extends Running {

    public PlayerHit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);

        if (this.cards.isBust()) {
            return new Bust(cards);
        }

        return new PlayerHit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
