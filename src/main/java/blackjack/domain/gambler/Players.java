package blackjack.domain.gambler;

import blackjack.domain.card.Deck;
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

    public Player findCurrentPlayer() {
        return players.stream()
                .filter(Gambler::isInProgress)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 턴이 남아있는 사용자가 없습니다."));
    }

    public int countInProgressPlayers() {
        return (int) players.stream()
                .filter(Gambler::isInProgress)
                .count();
    }

    public void endPlayerTurn(String playerName) {
        Player player = findPlayer(playerName);
        player.changeStatusToEnd();
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
