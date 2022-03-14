package blackjack.domain.result;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.ArrayList;
import java.util.List;

public class Judge {

    public static GameResult calculateGameResult(final Players players) {
        final DealerResult dealerResult = new DealerResult();
        final List<ParticipantResult> participantResults = new ArrayList<>();
        for (Player participant : players.getParticipants()) {
            boolean isDealerWin = compete(players.getDealer(), participant);
            updateDealerResult(dealerResult, isDealerWin);
            participantResults.add(makeParticipantResult(participant, isDealerWin));
        }

        return new GameResult(dealerResult, participantResults);
    }

    public static boolean compete(final Player dealer, final Player participant) {
        return isDealerWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isDealerWin(int dealerScore, int participantScore) {
        return participantScore > Cards.getMaxScore() || (dealerScore <= Cards.getMaxScore() && dealerScore >= participantScore);
    }

    private static void updateDealerResult(DealerResult dealerResult, boolean isDealerWin) {
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
