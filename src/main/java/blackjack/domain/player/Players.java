package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerResultInfo;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> values;
    private int currentTurnIndex;

    public Players(final List<Player> values) {
        validateDuplicationPlayers(values);
        this.values = values;
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
        return values.stream()
                .map(PlayerInfo::playerToInfo)
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
        return values.size() <= currentTurnIndex;
    }

    public PlayerInfo drawCurrentPlayer(final Card card) {
        final Player currentPlayer = currentTurnPlayer();
        currentPlayer.draw(card);
        checkCanTurnNext(currentPlayer);
        return PlayerInfo.playerToInfo(currentPlayer);
    }

    private void checkCanTurnNext(final Player currentPlayer) {
        if (!currentPlayer.canDraw()) {
            currentTurnIndex++;
        }
    }

    private Player currentTurnPlayer() {
        validateAllTurnEnd();
        return values.get(currentTurnIndex);
    }

    public PlayerInfo getCurrentTurnPlayerInfo() {
        return PlayerInfo.playerToInfo(currentTurnPlayer());
    }

    public List<PlayerResultInfo> getResultPlayerInfo() {
        return values.stream()
                .map(PlayerResultInfo::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getValues() {
        return List.copyOf(values);
    }
}
