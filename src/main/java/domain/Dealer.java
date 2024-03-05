package domain;

public class Dealer {

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        return cards.draw();
    }

    public void shuffle(){
        cards.shuffle();
    }
}
