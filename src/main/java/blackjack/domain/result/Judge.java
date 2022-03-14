package blackjack.domain.result;

import blackjack.domain.player.Players;

import java.util.ArrayList;
import java.util.List;

public class Judge {

    public static GameResult calculateGameResult(final Players players) {
        final DealerResult dealerResult = new DealerResult();
        final List<ParticipantResult> participantResults = new ArrayList<>();

        calculateDealerResult(dealerResult, players);
        calculateParticipantsResult(participantResults, players);

        return new GameResult(dealerResult, participantResults);
    }

    private static void calculateDealerResult(final DealerResult dealerResult, final Players players) {
        players.initParticipantPointer();
        while (!players.isParticipantPointerEnd()) {
            boolean isDealerWin = players.competeWithPointParticipant();
            updateDealerResult(dealerResult, isDealerWin);
            players.moveParticipantPointer();
        }
    }

    private static void calculateParticipantsResult(final List<ParticipantResult> participantResults, final Players players) {
        players.initParticipantPointer();
        while (!players.isParticipantPointerEnd()) {
            boolean isDealerWin = players.competeWithPointParticipant();
            participantResults.add(makeParticipantResult(players.pointParticipantName(), isDealerWin));
            players.moveParticipantPointer();
        }
    }

    private static void updateDealerResult(DealerResult dealerResult, boolean isDealerWin) {
        if (isDealerWin) {
            dealerResult.increaseWin();
            return;
        }
        dealerResult.increaseLose();
    }

    private static ParticipantResult makeParticipantResult(String participantName, boolean isDealerWin) {
        ParticipantResult participantResult = new ParticipantResult(participantName);
        if (!isDealerWin) {
            participantResult.makeWin();
        }
        return participantResult;
    }
}
