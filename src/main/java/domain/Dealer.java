package domain;

public class Dealer implements Gambler {

    private static final int PICK_BOUNDARY = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
        initialPick();
    }

    @Override
    public void pickCard() {
        if (cards.calculateScore() <= PICK_BOUNDARY) {
            cards.addCard(Deck.pickCard());
        }
    }

    @Override
    public void initialPick() {
        pickCard();
    }

    public int getCardsSize() {
        return cards.getCards().size();
    }
}
