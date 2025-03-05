package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackWinner {

    private final DealerWinStatus dealerWinStatus;

    private final Map<String, WinStatus> playerWinStatuses;

    public BlackjackWinner(BlackjackResult blackjackDealerResult, List<BlackjackResult> blackjackPlayerResults) {
        this.dealerWinStatus = calculateDealerWinStatus(blackjackDealerResult, blackjackPlayerResults);
        this.playerWinStatuses = calculateWinStatuses(blackjackDealerResult, blackjackPlayerResults);
    }

    private Map<String, WinStatus> calculateWinStatuses(BlackjackResult blackjackDealerResult,
                                                        List<BlackjackResult> blackjackPlayerResults) {
        Map<String, WinStatus> winStatuses = new HashMap<>();
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
        if (dealerSum > playerSum) {
            return 1;
        }
        return 0;
    }

    private int calculateDealerLose(int dealerSum, int playerSum) {
        if (dealerSum < playerSum) {
            return 1;
        }
        return 0;
    }

    private WinStatus calculatePlayerWinStatus(int dealerSum, int playerSum) {
        if (dealerSum < playerSum) {
            return WinStatus.WIN;
        }
        if (dealerSum > playerSum) {
            return WinStatus.LOSE;
        }
        return WinStatus.DRAW;
    }

    public DealerWinStatus getDealerWinStatus() {
        return dealerWinStatus;
    }

    public Map<String, WinStatus> getPlayerWinStatuses() {
        return Collections.unmodifiableMap(playerWinStatuses);
    }
}
