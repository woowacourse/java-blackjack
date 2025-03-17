package blackjack.domain.result;

import blackjack.domain.game.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerResults {
    private final List<PlayerResult> playerResults;

    public PlayerResults() {
        this.playerResults = new ArrayList<>();
    }

    public PlayerResults(PlayerResult... playerResults) {
        this.playerResults = new ArrayList<>(Arrays.asList(playerResults));
    }

    public void add(PlayerResult playerResult) {
        playerResults.add(playerResult);
    }

    public PlayerResult findResultByPlayer(Player player) {
        return playerResults.stream()
                .filter(result -> result.isResultOf(player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("플레이어에 해당하는 결과가 존재하지 않습니다."));
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }
}
