package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackGame(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
    }
    public void distributeInitialCard(){
        for (int i = 0; i < 2; i++) {
            distributeDealer();
            distributePlayers();
        }
    }

    public void distributeDealer() {
        dealer.addCard(cardDeck.poll());
    }

    public void distributePlayers() {
        for (Player player : players.getPlayers()) {
            player.addCard(cardDeck.poll());
        }
    }
}
