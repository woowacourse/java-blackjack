package blackjack.domain.user;

import blackjack.domain.card.CardDeck;

public class Participants {

    private Dealer dealer;
    private Players players;

    public Participants(CardDeck entireCardDeck, String requestPlayers) {
        this.dealer = new Dealer(entireCardDeck.generateInitialUserDeck());
        this.players = new Players(entireCardDeck, requestPlayers);
    }

    public boolean isAvailableDealerTurn() {
        return this.dealer.isAvailableDraw();
    }

    public void dealerTurn(CardDeck cardDeck) {
        dealer.draw(cardDeck.draw());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
