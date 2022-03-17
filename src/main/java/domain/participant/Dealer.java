package domain.participant;

import domain.card.Card;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public Map<Result, Integer> checkResult(List<Result> playersResult) {
        Map<Result, Integer> dealerResult = new EnumMap<Result, Integer>(Result.class);
        dealerResult.put(Result.WIN, countTargetResult(playersResult, Result.LOSE));
        dealerResult.put(Result.PUSH, countTargetResult(playersResult, Result.PUSH));
        dealerResult.put(Result.LOSE, countTargetResult(playersResult, Result.WIN));
        return dealerResult;
    }

    private int countTargetResult(List<Result> playersResult, Result targetResult) {
        return (int) playersResult.stream()
            .filter(result -> result == targetResult)
            .count();
    }

    public Card getFirstCard() {
        return cards.getCardByIndex(0);
    }

    @Override
    public boolean canHit() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}
