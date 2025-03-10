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
            if (participant.areYouDealer()) {
                continue;
            }
            determineWinner(dealer, participant);
        }
    }

    private Participant findDealer() {
        return scoreBoard.keySet().stream().filter(Participant::areYouDealer)
                .findFirst()
                .orElseThrow();
    }

    private void determineWinner(Participant dealer, Participant participant) {
        if (checkAllBust(dealer, participant)) {
            return;
        }

        if (checkPlayerWin(dealer, participant)) {
            return;
        }

        if (checkDealerWin(dealer, participant)) {
            return;
        }

        updateBattleResultDraw(dealer, participant);
    }

    private boolean checkDealerWin(Participant dealer, Participant participant) {
        int playerScore = participant.getScore();
        int dealerScore = dealer.getScore();

        if (checkWinner(dealerScore, playerScore)) {
            if (GameRule.checkBust(dealerScore)) {
                updateBattleResult(participant, dealer);
                return true;
            }
            updateBattleResult(dealer, participant);
            return true;
        }
        return false;
    }

    private boolean checkPlayerWin(Participant dealer, Participant participant) {
        int playerScore = participant.getScore();
        int dealerScore = dealer.getScore();

        if (checkWinner(playerScore, dealerScore)) {
            updateBattleResult(participant, dealer);
            return true;
        }
        return false;
    }

    private boolean checkAllBust(Participant dealer, Participant participant) {
        int playerScore = participant.getScore();
        int dealerScore = dealer.getScore();

        if (GameRule.checkBust(playerScore)) {
            if (GameRule.checkBust(dealerScore)) {
                updateBattleResultDraw(dealer, participant);
                return true;
            }
            updateBattleResult(dealer, participant);
            return true;
        }
        return false;
    }

    private boolean checkWinner(int score1, int score2) {
        return score1 > score2;
    }

    private void updateBattleResultDraw(Participant dealer, Participant player) {
        scoreBoard.get(dealer).addResult(BattleResult.DRAW);
        scoreBoard.get(player).addResult(BattleResult.DRAW);
    }

    private void updateBattleResult(Participant winner, Participant loser) {
        scoreBoard.get(winner).addResult(BattleResult.WIN);
        scoreBoard.get(loser).addResult(BattleResult.LOSE);
    }

    public Map<Participant, BattleResults> getScoreBoard() {
        return Collections.unmodifiableMap(scoreBoard);
    }
}
