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
    }

    private void initializeScoreBoard(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            scoreBoard.putIfAbsent(participant, new BattleResults());
        }
    }

    public void calculateScoreBoard() {
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
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        if (GameRule.checkBust(playerScore) && GameRule.checkBust(dealerScore)) {
            recordDraw(dealer, player);
            return;
        }

        if (GameRule.checkBust(playerScore)) {
            recordWin(dealer, player);
            return;
        }

        if (GameRule.checkBust(dealerScore)) {
            recordWin(player, dealer);
            return;
        }

        if (playerScore > dealerScore) {
            recordWin(player, dealer);
            return;
        }

        if (playerScore < dealerScore) {
            recordWin(dealer, player);
            return;
        }

        recordDraw(dealer, player);
    }

    private void recordDraw(Participant dealer, Participant player) {
        scoreBoard.get(dealer).addResult(BattleResult.DRAW);
        scoreBoard.get(player).addResult(BattleResult.DRAW);
    }

    private void recordWin(Participant winner, Participant loser) {
        scoreBoard.get(winner).addResult(BattleResult.WIN);
        scoreBoard.get(loser).addResult(BattleResult.LOSE);
    }

    public Map<Participant, BattleResults> getScoreBoard() {
        return Collections.unmodifiableMap(scoreBoard);
    }
}
