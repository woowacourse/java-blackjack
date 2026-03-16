package domain.model;

import domain.service.CardDistributor;

public class Dealer extends Person {

    private final CardDistributor cardDistributor;

    public Dealer(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void assignDeck() {
        assignDeck(cardDistributor.getInitialDeck());
    }

    public Deck getInitialDeck() {
        return cardDistributor.getInitialDeck();
    }

    public Card getAdditionalCard() {
        return cardDistributor.getAdditionalCard();
    }

    public void assignAdditionalCard() {
        super.appendCard(cardDistributor.getAdditionalCard());
    }

    public void applyPlayerProfit(double totalPlayerProfit) {
        super.minusProfit(totalPlayerProfit);
    }
}