package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

    public Dealer() {
        super(Cards.generateEmptyCards());
    }

    public List<Integer> decideSelfResult() {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};
        Map<Player, Result> playerResultMap = decideResult();

        for (Result playerResult : playerResultMap.values()) {
            decideResult(playerResult, dealerResult);
        }

        return new ArrayList<>(dealerResult.values());
    }

    private void decideResult(Result playerResult, Map<Result, Integer> dealerResult) {
        if (playerResult == WIN) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return;
        }
        if (playerResult == DRAW) {
            dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
            return;
        }
        dealerResult.put(WIN, dealerResult.get(WIN) + 1);
    }

    public boolean canDraw() {
        return cards.calculateScoreForBlackjack() <= SPECIFIC_SCORE_OF_DEALER;
    }
}
