package blackjack.domain;

import java.util.Map;

public class Dealer extends Participant {

    private static final int BUST_POINT = 21;
    private static final int CARD_DRAW_POINT = 16;
    private final ParticipantResults participantResults = new ParticipantResults();


    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= CARD_DRAW_POINT;
    }

    void calculateResult(final Player player) {
        final int dealerScore = currentScore();
        final int playerScore = player.currentScore();
        if (isTie(dealerScore, playerScore)) {
            participantResults.tiePlayer(player.getName());
            return;
        }
        if (isDealerWin(dealerScore, playerScore)) {
            participantResults.winPlayer(player.getName());
            return;
        }
        participantResults.losePlayer(player.getName());
    }

    private boolean isTie(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT && dealerScore > BUST_POINT) {
            return true;
        }
        return playerScore == dealerScore;
    }

    private boolean isDealerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT) {
            return true;
        }
        if (dealerScore > BUST_POINT) {
            return false;
        }
        return dealerScore > playerScore;
    }

    public Map<String, ResultType> getResult() {
        return participantResults.getPlayerNameToResultType();
    }
}
