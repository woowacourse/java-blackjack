package blackjack.gametable.gambler;

import blackjack.gametable.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void drawInitializeHands(Deck deck) {
        players.forEach(player -> player.drawInitializeHand(deck.drawInitialCards()));
    }

    public Player findPlayer(String playerName) {
        return players.stream()
                .filter(player -> player.getPlayerName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 사용자를 찾을 수 없습니다."));
    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(players.stream()
                .map(Player::getPlayerName)
                .toList());
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

}
