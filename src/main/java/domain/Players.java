package domain;

import domain.vo.NameAndCardInfos;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Players {

    private final Deque<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayDeque<>(players);
    }

    static Players from(List<String> names, DrawStrategy drawStrategy) {
        return new Players(playersFrom(names, drawStrategy));
    }

    private static List<Player> playersFrom(List<String> names, DrawStrategy drawStrategy) {
        return names.stream()
                .map(name -> Player.of(name, drawStrategy))
                .toList();
    }

    void add(String name, DrawStrategy drawStrategy) {
        players.add(Player.of(name, drawStrategy));
    }

    List<String> names() {
        return players.stream()
                .map(Participant::name)
                .toList();
    }

    void drawInitialCards() {
        players.forEach(this::drawInitialCards);
    }

    private void drawInitialCards(Player player) {
        player.draw();
        player.draw();
    }

    NameAndCardInfos currentPlayerCardInfos() {
        return currentPlayer().infos();
    }

    private Player currentPlayer() {
        return players.peek();
    }

    List<NameAndCardInfos> allPlayerCardInfos() {
        return players.stream()
                .map(Player::infos)
                .toList();
    }

    String currentPlayerName() {
        return currentPlayer().name();
    }

    void currentPlayerDrawCard() {
        currentPlayer().draw();
    }

    boolean isCurrentPlayerPlayable() {
        return hasWaitingPlayers() && currentPlayer().isPlayable();
    }

    boolean hasWaitingPlayers() {
        return !players.isEmpty();
    }
}
