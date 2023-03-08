package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.Map;

import static blackjack.domain.game.WinTieLose.*;

public class ResultGame {

    private static final Integer BLACKJACK_SCORE = 21;

    private final Map<Participant, WinTieLose> playersResult;

    public ResultGame(final Map<Participant, WinTieLose> playersResult) {
        this.playersResult = playersResult;
    }

    public void calculateResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        final int dealerScore = dealer.getScore();
        for (final Participant player : players) {
            playersResult.put(player, getPlayerResult(player.getScore(), dealerScore));
        }
    }

    private WinTieLose getPlayerResult(final int playerScore, final int dealerScore) {
        if ((playerScore > BLACKJACK_SCORE && dealerScore > BLACKJACK_SCORE) || playerScore == dealerScore) {
            return TIE;
        }
        if (playerScore <= BLACKJACK_SCORE && (dealerScore > BLACKJACK_SCORE || playerScore > dealerScore)) {
            return WIN;
        }
        return LOSE;
    }

    private WinTieLose getResultWhenPlayerBustScore(final int dealerScore) {
        if (isExceedScore(dealerScore, BLACKJACK_SCORE)) {
            return TIE;
        }
        return LOSE;
    }

    private WinTieLose getResultWhenPlayerBlackjackScore(final int playerScore, final int dealerScore) {
        if (isEqualScore(playerScore, dealerScore)) {
            return TIE;
        }
        return WIN;
    }

    private WinTieLose getResultWhenPlayerNormalScore(final int playerScore, final int dealerScore) {
        if (isExceedScore(dealerScore, BLACKJACK_SCORE)) {
            return WIN;
        }
        if (isExceedScore(playerScore, dealerScore)) {
            return WIN;
        }
        if (isEqualScore(playerScore, dealerScore)) {
            return TIE;
        }
        return LOSE;
    }

    private boolean isExceedScore(final int originScore, final int compareScore) {
        return originScore > compareScore;
    }

    private boolean isEqualScore(final int originScore, final int compareScore) {
        return originScore == compareScore;
    }

    private boolean isLessScore(final int originScore, final int compareScore) {
        return originScore < compareScore;
    }

    public int getDealerCount(final WinTieLose expected) {
        return (int) playersResult.values()
                .stream()
                .filter(winTieLose -> winTieLose.equals(expected.reverse()))
                .count();
    }

    public WinTieLose getPlayerResult(final Participant player) {
        return playersResult.get(player);
    }
}
