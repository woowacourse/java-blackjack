package blackjack.domain.human;

import blackjack.domain.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;


    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public int size() {
        return players.size();
    }

    public void giveCard() {
        for (Player player : players) {
            player.addCard(CardDeck.giveCard());
        }
    }

    public List<Player> getCardNeedPlayers() {
        return players.stream()
                .filter(Player::isOneMoreCard)
                .collect(Collectors.toList());
    }

    public String getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Player> getPlayers(){
        return List.copyOf(players);
    }
}
