package domain;

public class Dealer extends Participant {

    private final Cards deck;

    public Dealer(final Cards cards) {
        super();
        deck = cards;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffle(){
        deck.shuffle();
    }
}
