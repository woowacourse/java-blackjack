package blackjack.domain.result;

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
            Result participantResult = competeResult(players.getDealer(), participant);
            participantResults.add(makeParticipantResult(participant, participantResult));
            updateDealerResult(dealerResult, participantResult);
        }

        return new GameResult(dealerResult, participantResults);
    }

    private static void updateDealerResult(final DealerResult dealerResult, final Result result) {
        if (result == Result.WIN) {
            dealerResult.increaseLose();
            return;
        }

        if (result == Result.LOSE) {
            dealerResult.increaseWin();
            return;
        }
        dealerResult.increaseDraw();
    }

    private static boolean compete(final Player dealer, final Player participant) {
        return isParticipantWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isParticipantWin(int dealerScore, int participantScore) {
        return dealerScore > MAX_SCORE || (participantScore <= MAX_SCORE && participantScore >= dealerScore);
    }

    private static Result competeResult(Player dealer, Player participant) {
        if (checkDraw(dealer, participant)) {
            return Result.DRAW;
        }
        if (compete(dealer, participant)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private static boolean checkDraw(Player dealer, Player participant) {
        if (dealer.isBlackjack() && participant.isBlackjack()) {
            return true;
        }

        return !dealer.isBlackjack() && !participant.isBlackjack() && dealer.calculateFinalScore() == participant.calculateFinalScore();
    }

    private static ParticipantResult makeParticipantResult(Player participant, Result result) {
        return new ParticipantResult(participant.getName(), result);
    }
}
