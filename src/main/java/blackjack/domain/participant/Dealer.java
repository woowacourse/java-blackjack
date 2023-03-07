package blackjack.domain.participant;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int THRESHOLD_SCORE = 16;

    public Dealer() {
        super();
    }

    public Dealer(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isDrawable() {
        return isCardShortage() && isScoreLow();
    }

    private boolean isCardShortage() {
        return cards.count() <= MAXIMUM_CARD_COUNT;
    }

    private boolean isScoreLow() {
        return cards.calculateTotalScore() <= THRESHOLD_SCORE;
    }

    public Map<Result, Integer> getDealerResult(List<Player> players) {
        Map<Result, Integer> dealerResult = initDealerResult();
        for (Player player : players) {
            Result result = compareScoreTo(player);
            Integer currentResultCount = dealerResult.get(result);
            dealerResult.replace(result, currentResultCount + 1);
        }
        return dealerResult;
    }

    private Map<Result, Integer> initDealerResult() {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        dealerResult.put(WIN, 0);
        dealerResult.put(DRAW, 0);
        dealerResult.put(LOSE, 0);
        return dealerResult;
    }

    public Result compareScoreTo(final Player player) {
        final int dealerScore = this.getScore();
        final int playerScore = player.getScore();

        if (playerScore > 21) {
            return Result.WIN;
        }
        if (dealerScore > 21) {
            return Result.LOSE;
        }
        if (dealerScore > playerScore) {
            return Result.WIN;
        }
        if (dealerScore < playerScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public int getThresholdScore() {
        return THRESHOLD_SCORE;
    }
}
