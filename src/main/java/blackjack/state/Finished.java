package blackjack.state;

public abstract class Finished implements State{

    protected Cards cards;

    protected Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
