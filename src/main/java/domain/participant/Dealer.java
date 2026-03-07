package blackjack.domain.participant;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Card;

import java.util.EnumMap;
import java.util.Map;

public class Dealer extends Participant {

    private final static int HIT_THRESHOLD = 17;

    public Dealer() {
    }

    public Map<MatchResult, Integer> calculateResult(Map<String, MatchResult> playersResult) {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);

        for (Map.Entry<String, MatchResult> playerResult : playersResult.entrySet()) {
            MatchResult dealerMatchResult = MatchResult.reverse(playerResult.getValue());
            int resultCount = dealerResult.getOrDefault(dealerMatchResult, 0);

            dealerResult.put(dealerMatchResult, resultCount + 1);
        }

        return dealerResult;
    }

    public boolean shouldHit() {
        return hand.calculateScore() < HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }
}
