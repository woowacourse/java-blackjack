package domain;

public class Dealer implements Gambler{

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
        initialPick();
    }

    @Override
    public void pickCard() {
        cards.addCard(Deck.pickCard());
    }

    @Override
    public void initialPick() {
        pickCard();
    }
}
