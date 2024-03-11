package blackjack.domain;

import java.util.List;

public class GameParticipants {
    private final Players players;
    private final Dealer dealer;

    private GameParticipants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static GameParticipants of(List<String> playerNames) {
        return new GameParticipants(new Players(playerNames), new Dealer());
    }

    public void handOutInitialCards(Deck deck) {
        players.handOutInitialCards(deck);
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
