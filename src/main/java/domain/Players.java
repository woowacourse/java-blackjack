package domain;

import domain.vo.NameAndCardInfos;
import domain.vo.PlayedGameResult;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Players {

    private final Deque<Player> players;
    private final DrawStrategy drawStrategy;

    private Players(List<Player> players, DrawStrategy drawStrategy) {
        this.players = new ArrayDeque<>(players);
        this.drawStrategy = drawStrategy;
    }

    public static Players noOne(DrawStrategy drawStrategy) {
        return new Players(List.of(), drawStrategy);
    }

    Players join(List<String> names) {
        return new Players(playersFrom(names), this.drawStrategy);
    }

    private List<Player> playersFrom(List<String> names) {
        return names.stream()
                .map(name -> Player.of(name, drawStrategy))
                .toList();
    }

    List<String> names() {
        return players.stream()
                .map(Participant::name)
                .toList();
    }

    void drawInitialCards() {
        players.forEach(Participant::drawInitialCards);
    }

    NameAndCardInfos currentPlayerCardInfos() {
        return currentPlayer().infos();
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
        if (hasWaitingPlayers()) {
            currentPlayer().draw();
        }
    }

    boolean isCurrentPlayerPlayable() {
        return hasWaitingPlayers() && currentPlayer().isPlayable();
    }

    boolean hasWaitingPlayers() {
        return !players.isEmpty();
    }

    private Player currentPlayer() {
        return players.peek();
    }

    PlayedGameResult currentPlayersResult() {
        Player currentPlayer = players.poll();
        return new PlayedGameResult(currentPlayer.infos(), currentPlayer.scoreSum());
    }
}
