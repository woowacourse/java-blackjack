package domain.gameplaying;

import domain.common.NameAndCardInfos;
import domain.common.PlayedGameResult;
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

    static Players noOne(DrawStrategy drawStrategy) {
        return new Players(List.of(), drawStrategy);
    }

    Players join(List<String> names) {
        return new Players(playersFrom(names), this.drawStrategy);
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

    String currentPlayerName() {
        return currentPlayer().name();
    }

    private List<Player> playersFrom(List<String> names) {
        return names.stream()
                .map(name -> Player.of(name, drawStrategy))
                .toList();
    }

    private Player currentPlayer() {
        return players.peek();
    }

    PlayedGameResult currentPlayerResult() {
        requireCurrentPlayerExists();
        Player gameFinishedPlayer = players.poll();
        return PlayedGameResult.from(
                gameFinishedPlayer.name(),
                gameFinishedPlayer.cardInfos(),
                gameFinishedPlayer.scoreSum());
    }

    private void requireCurrentPlayerExists() {
        if(players.isEmpty()) {
            throw new IllegalStateException("플레이어를 가져올 수 없습니다.");
        }
    }
}
