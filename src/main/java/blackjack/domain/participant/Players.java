package blackjack.domain.participant;

import java.util.List;

public class Players {
    private final List<Player> players;
    private int nowTurnIndex;

    public Players(List<Player> players) {
        validateEmptyNames(players);
        this.players = players;
        this.nowTurnIndex = 0;
    }

    private void validateEmptyNames(List<Player> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public boolean isFinished() {
        return nowTurnIndex >= players.size();
    }

    public void skipTurn() {
        nowTurnIndex++;
    }

    public Player getCurrentPlayer() {
        return players.get(nowTurnIndex);
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
