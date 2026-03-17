package blackjack.domain;

import blackjack.domain.state.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant{

    private static final int DEALER_HIT_LIMIT = 17;

    public Dealer() {
        super("딜러");
    }

    public boolean isDealerNotDone() {
        return calculateTotalScore() < DEALER_HIT_LIMIT;
    }


    public GameResult judgeResult(List<Participant> players) {
        Map<Participant, ScoreCompareResult> playerResults = new HashMap<>();
        State dealerState = this.getState();

        for (Participant player : players) {
            State playerState= player.getState();
            ScoreCompareResult result = playerState.compare(dealerState);
            playerResults.put(player, result);
        }
        return new GameResult(playerResults);
    }
}
