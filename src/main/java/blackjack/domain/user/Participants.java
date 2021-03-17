package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.money.Money;
import java.util.List;

public class Participants {

    private Dealer dealer;
    private Players players;

    public Participants(CardDeck entireCardDeck, List<String> requestPlayers,
        List<Money> betCapital) {
        this.dealer = new Dealer(entireCardDeck.generateInitialUserDeck());
        this.players = new Players(entireCardDeck, requestPlayers, betCapital);
    }

    public boolean isAvailableDealerTurn() {
        return this.dealer.isAvailableDraw();
    }

    public void drawOnePlayer(CardDeck cardDeck, int playerIndex) {
        players.drawOnePlayer(cardDeck, playerIndex);
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
