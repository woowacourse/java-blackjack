package blackjack.domain;

import java.util.Map;

import static blackjack.domain.BlackJackConstant.BLACKJACK;

public class Dealer extends Participant {

    public static final int CARD_DRAW_POINT = 16;
    private final ParticipantResults participantResults = new ParticipantResults();


    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= CARD_DRAW_POINT;
    }

    public void calculateResult(final Player player) {
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
        if (playerScore > BLACKJACK && dealerScore > BLACKJACK) {
            return true;
        }
        return playerScore == dealerScore;
    }

    private boolean isDealerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BLACKJACK) {
            return true;
        }
        if (dealerScore > BLACKJACK) {
            return false;
        }
        return dealerScore > playerScore;
    }

    public Map<String, ResultType> getResult() {
        return participantResults.getPlayerNameToResultType();
    }
}
