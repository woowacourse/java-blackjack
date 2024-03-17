package blackjack.model.player;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.view.dto.ExecutingPlayer;

import java.util.ArrayDeque;
import java.util.Queue;

public class PlayerActionExecutor {
    private static final String EXECUTION_IS_FINISHED = "액션을 기다리는 플레이어가 없습니다";

    private final Queue<Player> waitingPlayers;
    private final CardGenerator cardGenerator;

    public PlayerActionExecutor(final Players players, final CardGenerator cardGenerator) {
        this.waitingPlayers = new ArrayDeque<>(players.getPlayers());
        this.cardGenerator = cardGenerator;
    }

    public void execute(final boolean actionCommand) {
        checkExecutionIsFinished();

        PlayerAction playerAction = PlayerAction.from(actionCommand);
        Player player = waitingPlayers.peek();
        if (playerAction.canNotContinue(player)) {
            waitingPlayers.poll();
            return;
        }
        player.hit(cardGenerator);
    }

    public ExecutingPlayer getExecutingPlayer() {
        checkExecutionIsFinished();
        Player player = waitingPlayers.peek();
        return ExecutingPlayer.from(player);
    }

    public boolean isFinished() {
        return waitingPlayers.isEmpty();
    }

    private void checkExecutionIsFinished() {
        if (isFinished()) {
            throw new UnsupportedOperationException(EXECUTION_IS_FINISHED);
        }
    }
}
