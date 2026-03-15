package domain.gameplaying;

import domain.common.NameAndCardInfos;
import domain.common.PlayedGameResult;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Players {

    private final Deque<Player> players;
    private final BlackJackDeck sharedDeck;

    private Players(List<Player> players, BlackJackDeck sharedDeck) {
        this.players = new ArrayDeque<>(players);
        this.sharedDeck = sharedDeck;
    }

    static Players noOne(BlackJackDeck sharedDeck) {
        return new Players(List.of(), sharedDeck);
    }

    Players join(List<String> names) {
        return new Players(playersFrom(names), this.sharedDeck);
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

    PlayedGameResult currentPlayerResult() {
        requireCurrentPlayerExists();
        Player gameFinishedPlayer = players.poll();
        return PlayedGameResult.from(
                gameFinishedPlayer.name(),
                gameFinishedPlayer.cardInfos(),
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

    private List<Player> playersFrom(List<String> names) {
        return names.stream()
                .map(name -> Player.of(name, sharedDeck))
                .toList();
    }

    private void requireCurrentPlayerExists() {
        if(players.isEmpty()) {
            throw new IllegalStateException("플레이어를 가져올 수 없습니다.");
        }
    }
}
