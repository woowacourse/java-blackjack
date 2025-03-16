package domain.gamer;

import domain.Betting;
import domain.GameResult;
import domain.card.CardGroup;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {

    private static final int DEALER_MUST_HIT_THRESHOLD = 16;

    public Dealer(CardGroup cardGroup) {
        super(cardGroup);
    }

    public Dealer(final CardGroup cardGroup, final Betting batting) {
        super(cardGroup, batting);
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

    @Override
    public boolean canReceiveCard() {
        return super.calculateScore() <= DEALER_MUST_HIT_THRESHOLD;
    }
}
