package domain.participant;

import domain.MatchResult;
import domain.card.Card;

import java.util.EnumMap;
import java.util.Map;

public class Dealer extends Participant {

    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
    }

    public Map<MatchResult, Integer> calculateResult(Map<Player, MatchResult> playersResult) {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);

        for (Map.Entry<Player, MatchResult> playerResult : playersResult.entrySet()) {
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
