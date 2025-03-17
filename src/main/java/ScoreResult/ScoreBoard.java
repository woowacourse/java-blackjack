package ScoreResult;

import participant.Participant;
import participant.Participants;
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
            if (!participant.isDealer()) {
                determineOutcome(dealer, participant);
            }
        }
    }

    public BattleResult requestBattleResult(Participant participant) {
        int winCounts = scoreBoard.get(participant).requestWinCounts();
        int drawCounts = scoreBoard.get(participant).requestDrawCounts();
        if (participant.isBlackJack() && winCounts != 0) {
            return BattleResult.BLACK_JACK;
        }
        if (winCounts != 0) {
            return BattleResult.WIN;
        }
        if (drawCounts != 0) {
            return BattleResult.DRAW;
        }

        return BattleResult.LOSE;
    }

    private Participant findDealer() {
        return scoreBoard.keySet().stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    private void determineOutcome(Participant dealer, Participant player) {
        BattleResultCalculator battleResultCalculator = new BattleResultCalculator();
        BattleResult playerResult = battleResultCalculator.calculate(dealer, player);
        BattleResult dealerResult = playerResult.reverse();
        scoreBoard.get(dealer).addResult(dealerResult);
        scoreBoard.get(player).addResult(playerResult);
    }

    public Map<Participant, BattleResults> getScoreBoard() {
        return scoreBoard;
    }
}
