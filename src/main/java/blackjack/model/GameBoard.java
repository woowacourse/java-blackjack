package blackjack.model;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Players;

public class GameBoard {
    private final Dealer dealer;
    private final Players players;

    public GameBoard(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeInitialCards() {
        dealer.receiveInitialCards(dealer.distributeInitialCard());
        players.receiveInitialCards(dealer::distributeInitialCard);
    }
}
