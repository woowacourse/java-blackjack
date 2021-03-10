package blackjack.state;

public abstract class Running implements State{

    protected Cards cards;

    protected Running(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
