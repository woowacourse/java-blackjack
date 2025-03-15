package domain.blackjackgame;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackWinner {

    private static final int BUST_STANDARD = 21;
    private static final int WIN_WEIGHT = 1;
    private static final int LOSE_WEIGHT = 1;
    private static final int ELSE_WEIGHT = 0;

    private final DealerWinStatus dealerWinStatus;
    private final Map<String, ParticipantGameResult> playerWinStatuses;

    public BlackjackWinner(BlackjackResult blackjackDealerResult, List<BlackjackResult> blackjackPlayerResults) {
        this.dealerWinStatus = calculateDealerWinStatus(blackjackDealerResult, blackjackPlayerResults);
        this.playerWinStatuses = calculateWinStatuses(blackjackDealerResult, blackjackPlayerResults);
    }

    private Map<String, ParticipantGameResult> calculateWinStatuses(BlackjackResult blackjackDealerResult,
                                                                    List<BlackjackResult> blackjackPlayerResults) {
        Map<String, ParticipantGameResult> winStatuses = new HashMap<>();
        int dealerSum = blackjackDealerResult.cardSum();
        for (BlackjackResult blackjackResult : blackjackPlayerResults) {
            String playerName = blackjackResult.name();
            int playerSum = blackjackResult.cardSum();
            winStatuses.put(playerName, calculatePlayerWinStatus(dealerSum, playerSum));
        }
        return winStatuses;
    }

    private DealerWinStatus calculateDealerWinStatus(BlackjackResult blackjackDealerResult,
                                                     List<BlackjackResult> blackjackPlayerResults) {
        int win = 0;
        int lose = 0;
        int dealerSum = blackjackDealerResult.cardSum();
        for (BlackjackResult playerBlackjackResult : blackjackPlayerResults) {
            int cardSum = playerBlackjackResult.cardSum();
            win += calculateDealerWin(dealerSum, cardSum);
            lose += calculateDealerLose(dealerSum, cardSum);
        }
        return new DealerWinStatus(win, lose);
    }

    private int calculateDealerWin(int dealerSum, int playerSum) {
        if (dealerSum > playerSum && !isBust(dealerSum)) {
            return WIN_WEIGHT;
        }
        if (!isBust(dealerSum) && isBust(playerSum)) {
            return WIN_WEIGHT;
        }
        return ELSE_WEIGHT;
    }

    private int calculateDealerLose(int dealerSum, int playerSum) {
        if (dealerSum < playerSum && !isBust(playerSum)) {
            return LOSE_WEIGHT;
        }
        if (!isBust(playerSum) && isBust(dealerSum)) {
            return LOSE_WEIGHT;
        }
        return ELSE_WEIGHT;
    }

    private static boolean isBust(int sum) {
        return sum > BUST_STANDARD;
    }

    public static ParticipantGameResult calculatePlayerWinStatus(int dealerSum, int playerSum) {
        if (isPlayerDraw(dealerSum, playerSum)) {
            return ParticipantGameResult.DRAW;
        }
        if (isPlayerWin(dealerSum, playerSum)) {
            return ParticipantGameResult.WIN;
        }
        return ParticipantGameResult.LOSE;
    }

    private static boolean isPlayerWin(int dealerSum, int playerSum) {
        if (isBust(dealerSum) && isBust(playerSum)) {
            return false;
        }
        if (isBust(playerSum)) {
            return false;
        }
        if (isBust(dealerSum)) {
            return true;
        }
        return playerSum > dealerSum;
    }

    private static boolean isPlayerDraw(int dealerSum, int playerSum) {
        if (dealerSum == playerSum) {
            return true;
        }
        if (isBust(dealerSum) && isBust(playerSum)) {
            return true;
        }
        return false;
    }

    public DealerWinStatus getDealerWinStatus() {
        return dealerWinStatus;
    }

    public Map<String, ParticipantGameResult> getPlayerWinStatuses() {
        return Collections.unmodifiableMap(playerWinStatuses);
    }
}
