package blackjack_statepattern;

public class Hit implements State {

    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(final Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isBust()) {
            return new Bust();
        }
        return new Hit(cards);
    }
}
