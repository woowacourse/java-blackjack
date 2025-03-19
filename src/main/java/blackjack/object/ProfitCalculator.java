package blackjack.object;

import blackjack.object.gambler.Name;

import java.util.*;

import static blackjack.object.WinningType.*;

public class ProfitCalculator {
    public static final int BLACK_JACK = 21;
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
                gamblerProfit.put(playerName, judgeBlackJackCase(playerName, bettingAmount))
        );
    }

    private void calculateDealerProfit(Map<Name, Integer> gamblerProfit) {
        int dealerProfit = 0;
        dealerProfit -= gamblerProfit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        gamblerProfit.put(Name.getDealerName(), dealerProfit);
    }

    private int judgeBlackJackCase(Name playerName, int bettingAmount) {
        int dealerScore = gamblerScores.get(Name.getDealerName());
        int playerScore = gamblerScores.get(playerName);

        if (playerScore == BLACK_JACK && dealerScore == BLACK_JACK) {
            return 0;
        }

        if (playerScore == BLACK_JACK) {
            double profit = bettingAmount * 1.5 ;

           return (int) profit;
        }

        if (dealerScore == BLACK_JACK) {
            return -bettingAmount;
        }

        return judgeNonBlackJackCase(playerName, bettingAmount);
    }

    private int judgeNonBlackJackCase(Name playerName, int bettingAmount) {
        WinningType result = judgePlayerResult(playerName);

        if (result == WIN) {
            return bettingAmount;
        }
        if (result == DEFEAT) {
            return -bettingAmount;
        }
        return 0; // DRAW
    }

    private WinningType judgePlayerResult(final Name name) {
        int dealerScore = gamblerScores.get(Name.getDealerName());

        if (hasBust(name)) {
            return judgeBustCase(name);
        }
        return judgeNonBustCase(name, dealerScore);
    }

    private boolean hasBust(final Name name) {
        return gamblerScores.get(Name.getDealerName()) > BLACK_JACK || gamblerScores.get(name) > BLACK_JACK;
    }

    private WinningType judgeBustCase(final Name name) {
        if (gamblerScores.get(name) > BLACK_JACK) {
            return DEFEAT;
        }
        return WIN;
    }

    private WinningType judgeNonBustCase(final Name name, final int dealerScore) {
        if (gamblerScores.get(name) > dealerScore) {
            return WIN;
        }
        if (gamblerScores.get(name) < dealerScore) {
            return DEFEAT;
        }
        return DRAW;
    }
}
