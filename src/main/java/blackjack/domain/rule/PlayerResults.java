package blackjack.domain.rule;

import java.util.List;

public class PlayerResults {

    private final List<PlayerResult> results;

    public PlayerResults(List<PlayerResult> results) {
        this.results = results;
    }

    public GameResult findResultByName(String name) {
        return results.stream()
                .filter(result -> result.getPlayerName().equals(name))
                .findFirst()
                .map(PlayerResult::getGameResult)
                .orElseThrow(() -> new IllegalArgumentException("[INTERNAL ERROR] 이름으로 결과를 조회하는데에 실패했습니다"));
    }
}
