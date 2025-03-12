package blackjack;

import blackjack.model.participant.HitDecisionStrategy;
import blackjack.model.participant.PlayerHandVisualizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestFixtures {

    public static final HitDecisionStrategy NO_HIT_STRATEGY = playerName -> false;
    public static final PlayerHandVisualizer TEST_EMPTY_VISUALIZER = player -> {
    };

    private TestFixtures() {
    }

    public static HitDecisionStrategy createHitDecisionStrategy(List<Boolean> hits) {
        Queue<Boolean> playerAnswers = new LinkedList<>(hits);
        return playerName -> playerAnswers.poll();
    }
}
