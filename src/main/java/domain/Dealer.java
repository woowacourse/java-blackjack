package domain;

import util.BlackJackRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_INDEX = 0;
    private static final int DEFAULT_VALUE = 0;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean canHit() {
        return BlackJackRule.canDealerHit(getScore());
    }

    public Card getFirstCard() {
        return cards.getCards().get(FIRST_INDEX);
    }

    public Map<WinningResult, Integer> getWinningResult(List<Integer> playerScores) {
        Map<WinningResult, Integer> resultMap = new HashMap<>();

        for (WinningResult winningResult : WinningResult.values()) {
            resultMap.put(winningResult, DEFAULT_VALUE);
        }

        for (int playerScore : playerScores) {
            calculateResult(resultMap, playerScore);
        }

        return resultMap;
    }

    private void calculateResult(Map<WinningResult, Integer> resultMap, int playerScore) {
        if (BlackJackRule.isBust(playerScore)) {
            resultMap.put(WinningResult.WIN, resultMap.get(WinningResult.WIN) + 1);
            return;
        }
        if (BlackJackRule.isBust(getScore())) {
            resultMap.put(WinningResult.LOSE, resultMap.get(WinningResult.LOSE) + 1);
            return;
        }
        if (playerScore > getScore()) {
            resultMap.put(WinningResult.LOSE, resultMap.get(WinningResult.LOSE) + 1);
            return;
        }
        if (playerScore == getScore()) {
            resultMap.put(WinningResult.DRAW, resultMap.get(WinningResult.DRAW) + 1);
            return;
        }
        resultMap.put(WinningResult.WIN, resultMap.get(WinningResult.WIN) + 1);
    }
}
