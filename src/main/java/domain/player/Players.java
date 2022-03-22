package domain.player;

import domain.card.CardDeck;

public class Players {
    private final Gamblers gamblers;
    private final Dealer dealer;

    public Players(Gamblers gamblers, Dealer dealer) {
        this.gamblers = gamblers;
        this.dealer = dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void spreadCard(CardDeck cardDeck) {
        gamblers.addCard(cardDeck);
        dealer.addCard(cardDeck.drawCard());
    }
}
