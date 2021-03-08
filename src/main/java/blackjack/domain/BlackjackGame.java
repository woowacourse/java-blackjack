package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;

import java.util.List;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame generateByUser(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = Players.of(names);
        return new BlackjackGame(dealer, players);
    }

    public void handOutInitialCards() {
        dealer.receiveCards(Deck.popTwo());
        players.players()
                .forEach(player -> player.receiveCards(Deck.popTwo()));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
