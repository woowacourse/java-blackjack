package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;
    private int currentTurnIndex;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null로 생성할 수 없습니다.");
        this.players = new ArrayList<>(players);
        validateDuplicationPlayers(this.players);
    }

    private void validateDuplicationPlayers(final List<Player> players) {
        if (calculateDistinctCount(players) != players.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private int calculateDistinctCount(final List<Player> players) {
        return (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
    }

    public List<PlayerInfo> getInitPlayerInfo() {
        return players.stream()
                .map(PlayerInfo::toPlayerInitInfo)
                .collect(Collectors.toUnmodifiableList());
    }

    public void turnToNextPlayer() {
        validateAllTurnEnd();
        currentTurnPlayer().endTurn();
        currentTurnIndex++;
    }

    private void validateAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    public boolean isAllTurnEnd() {
        return players.size() <= currentTurnIndex;
    }

    public PlayerInfo drawCurrentPlayer(final Card card) {
        final Player currentPlayer = currentTurnPlayer();
        currentPlayer.draw(card);
        checkCanTurnNext(currentPlayer);
        return PlayerInfo.toPlayerInfo(currentPlayer);
    }

    private void checkCanTurnNext(final Player currentPlayer) {
        if (!currentPlayer.canDraw()) {
            currentTurnIndex++;
        }
    }

    private Player currentTurnPlayer() {
        validateAllTurnEnd();
        return players.get(currentTurnIndex);
    }

    public PlayerInfo getCurrentTurnPlayerInfo() {
        return PlayerInfo.toPlayerInfo(currentTurnPlayer());
    }

    public List<PlayerResultInfo> getResultPlayerInfo() {
        return players.stream()
                .map(PlayerResultInfo::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public Stream<Player> players() {
        return players.stream();
    }
}
