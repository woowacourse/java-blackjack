package domain;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResultEvaluator {

    private BlackjackResultEvaluator() {

    }

    public static Map<ParticipantName, WinStatus> calculateWinStatus(BlackjackParticipants participants) {
        Map<ParticipantName, WinStatus> winStatuses = new HashMap<>();
        ParticipantName dealerName = participants.dealerName();
        for (ParticipantName playerName : participants.getPlayerNames()) {
            ScoreInfo scoreInfo = new ScoreInfo(playerName, dealerName, participants);
            winStatuses.put(playerName, calculatePlayerWinStatus(scoreInfo));
        }

        return winStatuses;
    }

    public static DealerWinStatus calculateDealerWinStatus(BlackjackParticipants participants) {
        int win = 0;
        int lose = 0;
        ParticipantName dealerName = participants.dealerName();
        for (ParticipantName playerName : participants.getPlayerNames()) {
            ScoreInfo scoreInfo = new ScoreInfo(playerName, dealerName, participants);
            win += calculateDealerWin(scoreInfo);
            lose += calculateDealerLose(scoreInfo);
        }

        return new DealerWinStatus(win, lose);
    }

    private static int calculateDealerWin(ScoreInfo scoreInfo) {
        if (scoreInfo.dealerSum.isGreaterThan(scoreInfo.playerSum) && !scoreInfo.isDealerBust) {
            return 1;
        }
        if (!scoreInfo.isDealerBust && scoreInfo.isPlayerBust) {
            return 1;
        }
        return 0;
    }

    private static int calculateDealerLose(ScoreInfo scoreInfo) {
        if (scoreInfo.dealerSum.isLessThan(scoreInfo.playerSum) && !scoreInfo.isPlayerBust) {
            return 1;

        }
        if (!scoreInfo.isPlayerBust && scoreInfo.isDealerBust) {
            return 1;
        }
        return 0;
    }

    private static WinStatus calculatePlayerWinStatus(ScoreInfo scoreInfo) {
        if (scoreInfo.isDealerBust && scoreInfo.isPlayerBust) {
            return WinStatus.DRAW;
        }
        if (scoreInfo.dealerSum.isLessThan(scoreInfo.playerSum)) {
            return WinStatus.WIN;
        }
        if (scoreInfo.dealerSum.isGreaterThan(scoreInfo.playerSum)) {
            return WinStatus.LOSE;
        }
        return WinStatus.DRAW;
    }

    private static class ScoreInfo {
        Score dealerSum;
        Score playerSum;
        boolean isDealerBust;
        boolean isPlayerBust;

        public ScoreInfo(ParticipantName playerName, ParticipantName dealerName, BlackjackParticipants participants) {
            this.dealerSum = participants.calculateCardSum(dealerName);
            this.playerSum = participants.calculateCardSum(playerName);
            this.isDealerBust = participants.isBust(dealerName);
            this.isPlayerBust = participants.isBust(playerName);
        }
    }
}
