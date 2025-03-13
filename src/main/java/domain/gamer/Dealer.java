package domain.gamer;

import domain.GameResult;
import domain.card.CardGroup;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {
    public Dealer(CardGroup cardGroup) {
        super(cardGroup);
    }

    public boolean isLessThen(int score) {
        return this.getCardGroup().calculateScore(Gamer.LIMIT) <= score;
    }

    public Map<GameResult, Integer> calculateDealerGameResult(final Map<String, GameResult> playerGameResults) {
        final List<GameResult> results = playerGameResults.values().stream().toList();
        return GameResult.getAllGameResults().stream()
                .filter(results::contains)
                .collect(Collectors.toMap(
                        GameResult::swapGameResult,
                        result -> Collections.frequency(results, result),
                        (newResult, oldResult) -> oldResult
                ));
    }
}
