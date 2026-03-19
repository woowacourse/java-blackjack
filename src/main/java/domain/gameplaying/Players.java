package domain.gameplaying;

import dto.PlayedGameResult;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Players {

    private final Deque<Player> players;
    private final DrawStrategy sharedDeck;

    private Players(List<Player> players, DrawStrategy sharedDeck) {
        this.players = new ArrayDeque<>(players);
        this.sharedDeck = sharedDeck;
    }

    static Players noOne(DrawStrategy sharedDeck) {
        return new Players(List.of(), sharedDeck);
    }

    void join(List<String> names) {
        names.stream()
                .map(name -> Player.of(name, sharedDeck))
                .forEach(players::add);
    }

    List<String> names() {
        return players.stream()
                .map(Participant::name)
                .toList();
    }

    void drawInitialCards() {
        players.forEach(Participant::drawInitialCards);
    }

    PlayedGameResult currentPlayerCardInfos() {
        return currentPlayer().infos();
    }
    
    List<PlayedGameResult> allPlayerCardInfos() {
        return players.stream()
                .map(Player::infos)
                .toList();
    }

    void currentPlayerDrawCard() {
        if (hasWaitingPlayers()) {
            currentPlayer().draw();
        }
    }

    PlayedGameResult currentPlayerResult() {
        requireCurrentPlayerExists();
        Player gameFinishedPlayer = players.poll();
        return PlayedGameResult.from(
                gameFinishedPlayer.name(),
                gameFinishedPlayer.cards(),
                gameFinishedPlayer.scoreSum());
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

    private Player currentPlayer() {
        return players.peek();
    }

    private void requireCurrentPlayerExists() {
        if(players.isEmpty()) {
            throw new IllegalStateException("플레이어를 가져올 수 없습니다.");
        }
    }
}
