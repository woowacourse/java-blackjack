package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.Map;

import static blackjack.domain.game.WinTieLose.*;

public class ResultGame {

    private static final Integer BLACKJACK_SCORE = 21;
    private static final String NOT_FIND_RESULT_ERROR_MESSAGE = "점수에 해당하는 승무패를 구할 수 없습니다.";

    private final Map<Participant, WinTieLose> playersResult;
    private final Participants participants;

    public ResultGame(final Participants participants, final Map<Participant, WinTieLose> playersResult) {
        this.participants = participants;
        this.playersResult = playersResult;
        calculateResult();
    }

    private void calculateResult() {
        final Participant dealer = participants.getDealer();
        final int dealerScore = dealer.getScore();
        final List<Participant> players = participants.getPlayers();

        for (final Participant player : players) {
            final int playerScore = player.getScore();

            playersResult.put(player, getPlayerResult(playerScore, dealerScore));
        }
    }

    private WinTieLose getPlayerResult(final int playerScore, final int dealerScore) {
        if (isExceedScore(playerScore, BLACKJACK_SCORE)) {
            return getResultWhenPlayerBustScore(dealerScore);
        }
        if (isEqualScore(playerScore, BLACKJACK_SCORE)) {
            return getResultWhenPlayerBlackjackScore(playerScore, dealerScore);
        }
        if (isLessScore(playerScore, BLACKJACK_SCORE)) {
            return getResultWhenPlayerNormalScore(playerScore, dealerScore);
        }
        throw new IllegalArgumentException(NOT_FIND_RESULT_ERROR_MESSAGE);
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
