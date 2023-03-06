package domain;

import java.util.List;

public class Dealer implements Gambler {

    private static final int PICK_BOUNDARY = 16;
    private static final String NAME = "딜러";

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
        initialPick();
    }

    @Override
    public void initialPick() {
        pickCard();
    }

    @Override
    public void pickCard() {
        if (cards.calculateScore() <= PICK_BOUNDARY) {
            cards.addCard(Deck.pickCard());
        }
    }

    @Override
    public boolean isBustedGambler() {
        return this.cards.isBusted(cards.calculateScore());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardsSize() {
        return cards.getCards().size();
    }

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    public int getPickBoundary() {
        return PICK_BOUNDARY;
    }
}
