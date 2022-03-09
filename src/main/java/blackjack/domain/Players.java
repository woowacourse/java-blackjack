package blackjack.domain;

import blackjack.dto.PlayerInfo;
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
        final int distinctCount = (int) players.stream()
                .map(player -> player.getName())
                .distinct()
                .count();
        if (distinctCount != players.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    public List<PlayerInfo> getInitPlayerInfo() {
        return values.stream()
                .map(PlayerInfo::playerToInfo)
                .collect(Collectors.toList());
    }

    public void turnToNextPlayer() {
        validateAllTurnEnd();
        currentTurnIndex++;
    }

    public void drawCurrentPlayer(final Card card) {
        final Player currentPlayer = currentTurnPlayer();
        currentPlayer.draw(card);
        checkCurrentPlayerCanDraw(currentPlayer);
    }

    private void validateAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    private void checkCurrentPlayerCanDraw(final Player currentPlayer) {
        if (!currentPlayer.canDraw()) {
            currentTurnIndex++;
        }
    }

    public boolean isAllTurnEnd() {
        return values.size() <= currentTurnIndex;
    }

    public PlayerInfo getCurrentTurnPlayerInfo() {
        return PlayerInfo.playerToInfo(currentTurnPlayer());
    }

    private Player currentTurnPlayer() {
        validateAllTurnEnd();
        return values.get(currentTurnIndex);
    }
}
