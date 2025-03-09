package blackjack.domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersResults {

    private final List<PlayerResult> playerResults;

    private PlayersResults() {
        this.playerResults = new ArrayList<>();
    }

    public static PlayersResults create() {
        return new PlayersResults();
    }

    public void save(PlayerResult playerResult) {
        playerResults.add(playerResult);
    }

    public List<PlayerResult> getAllResult() {
        return Collections.unmodifiableList(playerResults);
    }
}
