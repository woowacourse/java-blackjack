package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.ArrayList;
import java.util.List;

public class Judge {

    public static final int MAX_SCORE = 21;

    public static GameResult calculateGameResult(final Players players) {
        final DealerResult dealerResult = new DealerResult();
        final List<ParticipantResult> participantResults = new ArrayList<>();

        for (Player participant : players.getParticipants()) {
            boolean isDealerWin = compete(players.getDealer(), participant);
            changeDealerResult(dealerResult, isDealerWin);
            participantResults.add(makeParticipantResult(participant, isDealerWin));
        }
        return new GameResult(dealerResult, participantResults);
    }

    private static boolean compete(final Player dealer, final Player participant) {
        return isDealerWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

    private static void changeDealerResult(DealerResult dealerResult, boolean isDealerWin) {
        if (isDealerWin) {
            dealerResult.increaseWin();
            return;
        }
        dealerResult.increaseLose();
    }

    private static ParticipantResult makeParticipantResult(Player participant, boolean isDealerWin) {
        ParticipantResult participantResult = new ParticipantResult(participant.getName());
        if (!isDealerWin) {
            participantResult.makeWin();
        }
        return participantResult;
    }

}
