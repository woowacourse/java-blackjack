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
        if (cards.getCards().isEmpty()) {
            pickCard();
        }
    }

    @Override
    public void pickCard() {
        if (isDealerHit()) {
            cards.addCard(Deck.pickCard());
        }
    }

    public boolean isDealerHit() {
        return cards.calculateScore() <= PICK_BOUNDARY;
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

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    public int getCardsSize() {
        return cards.getCards().size();
    }
}
