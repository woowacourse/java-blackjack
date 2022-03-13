package blackjack.domain.game;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.participant.Player;

public class TurnManager {

    private static final int DEFAULT_TURN_INDEX = 0;
    public static final String TURN_IS_END_MESSAGE = "턴이 종료되어 플레이어를 가져올 수 없습니다.";

    private final List<Player> players;
    private int turnIndex;

    public TurnManager(List<Player> players, boolean isDealerBlackjack) {
        this.players = new ArrayList<>(players);
        if (isDealerBlackjack) {
            turnIndex = players.size();
            return;
        }
        turnIndex = DEFAULT_TURN_INDEX;
    }

    public boolean isEndAllTurn() {
        return turnIndex == players.size();
    }

    public Player getCurrentPlayer() {
        validateTurnIndex();
        return players.get(turnIndex);
    }

    private void validateTurnIndex() {
        if (turnIndex >= players.size()) {
            throw new IllegalArgumentException(TURN_IS_END_MESSAGE);
        }
    }

    public void turnToNext() {
        if (!isEndAllTurn()) {
            turnIndex++;
        }
        while (!isEndAllTurn()
            && (getCurrentPlayer().isBust() || getCurrentPlayer().isBlackjack())) {
            turnIndex++;
        }
    }

    public boolean isCurrentPlayerCanHit() {
        return getCurrentPlayer().canHit();
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }
}
