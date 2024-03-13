package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class GameParticipants {
    private final Players players;
    private final Dealer dealer;

    private GameParticipants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static GameParticipants of(Players players) {
        return new GameParticipants(players, new Dealer());
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
