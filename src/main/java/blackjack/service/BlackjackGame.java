package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    public Players generatePlayers(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Player player = Player.of(Name.of(name));
            players.add(player);
        }
        return Players.of(players);
    }

    public Dealer generateDealer() {
        return Dealer.of();
    }

    public Players deal(Players players, Deck deck) {
        return players.receiveCards(deck);
    }

    public Dealer deal(Dealer dealer, Deck deck) {
        return dealer.receiveCards(deck.drawSecondTimes());
    }
}
