package domain.game;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class GamblersGameResult {

    private Map<String, Long> gamblersResult;

    public GamblersGameResult(Dealer dealer, Gamblers gamblers) {
        int dealerTotalScore = dealer.getTotalScore();

        this.gamblersResult = gamblers.getGamblers()
                .stream()
                .collect(Collectors.toMap(Gambler::getName, gambler -> {
                    GameResult gameResult = GameResult.determine(dealerTotalScore, gambler.getTotalScore());
                    if (gameResult == GameResult.WIN && gambler.isBlackjack()) {
                        return gambler.getAmount() * 3 / 2;
                    }
                    if(gameResult == GameResult.WIN) return gambler.getAmount();
                    if(gameResult == GameResult.LOSE) return -gambler.getAmount();
                    return 0L;
                }));
    }

    public Long getDealerResult() {
        return -gamblersResult.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public Long getMatchResult(String name) {
        return gamblersResult.get(name);
    }

    public Map<String, Long> getResultInfo() {
        return Collections.unmodifiableMap(gamblersResult);
    }
}
