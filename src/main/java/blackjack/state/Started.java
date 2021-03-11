package blackjack.state;

public abstract class Started implements State{
    protected Cards cards;

    public Started(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
