package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void receiveEachCardDeckFromDealer(final List<String> playerNames, final Dealer dealer) {
        for (String playerName : playerNames) {
            Player player = new Player(playerName, new CardDeck());
            player.receiveInitialCardDeck(dealer.giveCardsToPlayer());
            players.add(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
