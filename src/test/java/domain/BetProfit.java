package domain;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BetProfit {
    private final static int ZERO = 0;
    private final static double BLACKJACK_YIELD = 1.5;

    private final Map<Name, Integer> betProfit = new HashMap<>();

    public BetProfit(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> {
                    betProfit.put(playerName, ZERO);
                }
        );
    }

    public void calculateProfit(Map<Name, GameResult> playerResults, BetHistory betHistory) {
        validatePlayers(playerResults.keySet());

        playerResults.keySet().forEach(
                playerName -> {
                    int betAmount = betHistory.getBetAmount(playerName);
                    GameResult gameResult = playerResults.get(playerName);

                    betAmount = calculateBetAmount(gameResult, betAmount);

                    betProfit.put(playerName, betAmount);
                }
        );
    }

    private void validatePlayers(Set<Name> playerNames) {
        boolean match = playerNames.stream()
                .allMatch(betProfit::containsKey);

        if (!match) {
            throw new IllegalArgumentException("[ERROR] 배팅에 참여한 플레이어만 수익 계산이 가능합니다.");
        }
    }

    private int calculateBetAmount(GameResult gameResult, int betAmount) {
        if (gameResult == GameResult.LOSE) {
            betAmount = negateBetAmount(betAmount);
        }

        if (gameResult == GameResult.DRAW) {
            betAmount = ZERO;
        }

        if (gameResult == GameResult.BLACKJACK_WIN) {
            betAmount = blackjackAmount(betAmount);
        }

        return betAmount;
    }

    private int blackjackAmount(int betAmount) {
        return (int) (betAmount * BLACKJACK_YIELD);
    }

    private int negateBetAmount(int betAmount) {
        return -betAmount;
    }

    public Map<Name, Integer> getBetProfit() {
        return Map.copyOf(betProfit);
    }
}
