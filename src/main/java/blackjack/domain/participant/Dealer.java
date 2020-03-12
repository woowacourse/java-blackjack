package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private static final String CANT_FIND_CARD_MSG = "카드가 존재하지 않습니다.";
    public static final int LOWER_BOUND = 16;

    @Override
    public boolean canGetMoreCard() {
        return computeScore() <= LOWER_BOUND;
    }

    public Map<Result, Integer> computeResult(List<MatchResult> playerResults) {
        Map<Result, Integer> dealerResult = new HashMap<>();

        for (Result result : Result.values()) {
            int resultCount = (int) playerResults.stream()
                    .filter(playerResult -> playerResult.getResult() == result)
                    .count();
            dealerResult.put(Result.reverse(result), resultCount);
        }

        return dealerResult;
    }

    public Card getFirstCard() {
        return cards.getCards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_FIND_CARD_MSG));
    }
}
