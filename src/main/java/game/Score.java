package game;

import user.Participants;
import user.Participant;
import java.util.LinkedHashMap;
import java.util.Map;

public class Score {
    private final Participants participants;

    public Score(Participants participants) {
        this.participants = participants;
    }

    public Map<Participant, GameResult> calculatePlayerScore() {
        Map<Participant, GameResult> gameResult = new LinkedHashMap<>();
        if (participants.getDealer().isBust()) {
            participants.getPlayers().forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        participants.getPlayers().forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(Participant participant, Map<Participant, GameResult> gameResult) {
        if (participant.isBust()) {
            gameResult.put(participant, GameResult.LOSE);
            return;
        }
        gameResult.put(participant, GameResult.WIN);
    }

    private GameResult compareScore(Participant player) {
        int dealerScore = participants.getDealer().calculateScore();
        int playerScore = player.calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(Participant player) {
        Participant dealer = participants.getDealer();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
