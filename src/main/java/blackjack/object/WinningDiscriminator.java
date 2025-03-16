package blackjack.object;

import blackjack.object.gambler.Name;

import java.util.*;

import static blackjack.object.WinningType.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class WinningDiscriminator {
    public static final int BLACK_JACK = 21;
    private final Map<Name, Integer> gamblerScores;
    private final Map<Name, Integer> bettingRecords;

    public WinningDiscriminator(final Map<Name, Integer> gamblerScores, final Map<Name, Integer> bettingRecords) {
        this.gamblerScores = gamblerScores;
        this.bettingRecords = bettingRecords;
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return bettingRecords.keySet()
                .stream()
                .collect(toMap(identity(), this::judgePlayerResult));
    }

    public Map<Name, Integer> calculateGamblerProfit() {
        Map<Name, Integer> gamblerProfit = new HashMap<>();
        int dealerProfit = 0;

        for (Name playerName : bettingRecords.keySet()) {
            int bettingAmount = bettingRecords.get(playerName);
            int playerProfit = judgeBlackJackCase(playerName, bettingAmount);

            gamblerProfit.put(playerName, playerProfit);
            dealerProfit -= playerProfit;
        }
        gamblerProfit.put(Name.getDealerName(), dealerProfit);
        return gamblerProfit;
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
        return judgeNormalCase(playerName, bettingAmount);
    }

    private int judgeNormalCase(Name playerName, int bettingAmount) {
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
        int playerScore = gamblerScores.get(name);
        int dealerScore = gamblerScores.get(Name.getDealerName());

        if (playerScore > BLACK_JACK) {
            return DEFEAT;
        }
        if (dealerScore > BLACK_JACK) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return DEFEAT;
        }
        return DRAW;
    }
}
