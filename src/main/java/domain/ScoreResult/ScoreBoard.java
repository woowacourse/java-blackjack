package domain.ScoreResult;

import domain.game.GameRule;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    private final Map<Participant, BattleResults> scoreBoard;

    public ScoreBoard(final Participants participants) {
        this.scoreBoard = new HashMap<>();
        initializeScoreBoard(participants);
        calculateScoreBoard();
    }

    private void initializeScoreBoard(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            scoreBoard.putIfAbsent(participant, new BattleResults());
        }
    }

    private void calculateScoreBoard() {
        Participant dealer = findDealer();
        for (Participant participant : scoreBoard.keySet()) {
            if (!participant.areYouDealer()) {
                determineOutcome(dealer, participant);
            }
        }
    }

    private Participant findDealer() {
        return scoreBoard.keySet().stream()
                .filter(Participant::areYouDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    private void determineOutcome(Participant dealer, Participant player) {
        BattleResult playerResult = determinePlayerResult(dealer.getScore(), player.getScore());
        BattleResult dealerResult = reverseResult(playerResult);
        scoreBoard.get(dealer).addResult(dealerResult);
        scoreBoard.get(player).addResult(playerResult);
    }

    private BattleResult determinePlayerResult(int dealerScore, int playerScore) {
        if (hasBust(dealerScore, playerScore)) {
            return determinePlayerResultBustCase(dealerScore, playerScore);
        }
        return determinePlayerResultNormalCase(dealerScore, playerScore);
    }

    private boolean hasBust(int dealerScore, int playerScore) {
        return GameRule.checkBust(dealerScore) || GameRule.checkBust(playerScore);
    }

    private BattleResult determinePlayerResultBustCase(int dealerScore, int playerScore) {
        if (GameRule.checkBust(playerScore) && GameRule.checkBust(dealerScore)) {
            return BattleResult.DRAW;
        }
        if (GameRule.checkBust(playerScore)) {
            return BattleResult.LOSE;
        }
        return BattleResult.WIN;
    }

    private static BattleResult determinePlayerResultNormalCase(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return BattleResult.WIN;
        }
        if (playerScore < dealerScore) {
            return BattleResult.LOSE;
        }
        return BattleResult.DRAW;
    }

    private BattleResult reverseResult(BattleResult result) {
        if (result == BattleResult.WIN) {
            return BattleResult.LOSE;
        }
        if (result == BattleResult.LOSE) {
            return BattleResult.WIN;
        }
        return BattleResult.DRAW;
    }

    public Map<Participant, BattleResults> getScoreBoard() {
        return Collections.unmodifiableMap(scoreBoard);
    }
}
