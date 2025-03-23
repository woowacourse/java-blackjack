package blackjack.object;

import blackjack.object.gambler.Name;

import java.util.*;

public class ProfitCalculator {
    public static final int BLACK_JACK = 21;
    public static final double BLACKJACK_GAIN_RATE = 1.5;

    private final Map<Name, Integer> gamblerScores;
    private final Map<Name, Integer> bettingRecords;

    public ProfitCalculator(final Map<Name, Integer> gamblerScores, final Map<Name, Integer> bettingRecords) {
        this.gamblerScores = gamblerScores;
        this.bettingRecords = bettingRecords;
    }

    public Map<Name, Integer> calculateGamblerProfit() {
        Map<Name, Integer> gamblerProfit = new HashMap<>();

        calculatePlayerProfit(gamblerProfit);

        calculateDealerProfit(gamblerProfit);

        return gamblerProfit;
    }

    private void calculatePlayerProfit(Map<Name, Integer> gamblerProfit) {
        bettingRecords.forEach((playerName, bettingAmount) ->
                gamblerProfit.put(playerName, calculatePlayerProfitByScore(playerName, bettingAmount))
        );
    }

    private void calculateDealerProfit(Map<Name, Integer> gamblerProfit) {
        int dealerProfit = 0;
        dealerProfit -= gamblerProfit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        gamblerProfit.put(Name.getDealerName(), dealerProfit);
    }

    private int calculatePlayerProfitByScore(Name playerName, int bettingAmount) {
        Integer x = judgeBlackJackCase(playerName, bettingAmount);
        if (x != null) return x;

        return judgeNonBlackJackCase(playerName, bettingAmount);
    }

    private Integer judgeBlackJackCase(Name playerName, int bettingAmount) {
        int dealerScore = gamblerScores.get(Name.getDealerName());
        int playerScore = gamblerScores.get(playerName);

        if (playerScore == BLACK_JACK && dealerScore == BLACK_JACK) {
            return 0;
        }

        if (playerScore == BLACK_JACK) {
            double profit = BLACKJACK_GAIN_RATE * bettingAmount;

            return (int) profit;
        }

        if (dealerScore == BLACK_JACK) {
            return -bettingAmount;
        }
        return null;
    }

    private int judgeNonBlackJackCase(final Name playerName, final int bettingAmount) {
        if (hasBust(playerName)) {
            return judgeBustCase(playerName, bettingAmount);
        }
        return judgeNonBustCase(playerName, bettingAmount);
    }

    private boolean hasBust(final Name name) {
        return gamblerScores.get(Name.getDealerName()) > BLACK_JACK || gamblerScores.get(name) > BLACK_JACK;
    }

    private int judgeBustCase(final Name name, final int bettingAmount) {
        if (gamblerScores.get(name) > BLACK_JACK) {
            return -bettingAmount;
        }
        return bettingAmount;
    }

    private int judgeNonBustCase(final Name name, final int bettingAmount) {
        int dealerScore = gamblerScores.get(Name.getDealerName());
        if (gamblerScores.get(name) > dealerScore) {
            return bettingAmount;
        }
        if (gamblerScores.get(name) < dealerScore) {
            return -bettingAmount;
        }
        return 0;
    }
}
