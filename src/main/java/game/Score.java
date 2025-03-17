package game;

import user.Participants;
import user.User;
import java.util.LinkedHashMap;
import java.util.Map;

public class Score {
    private final Participants participants;

    public Score(Participants participants) {
        this.participants = participants;
    }

    public Map<User, GameResult> calculatePlayerScore() {
        Map<User, GameResult> gameResult = new LinkedHashMap<>();
        if (participants.getDealer().isBust()) {
            participants.getPlayers().forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        participants.getPlayers().forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(User user, Map<User, GameResult> gameResult) {
        if (user.isBust()) {
            gameResult.put(user, GameResult.LOSE);
            return;
        }
        gameResult.put(user, GameResult.WIN);
    }

    private GameResult compareScore(User player) {
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

    private GameResult compareSameScore(User player) {
        User dealer = participants.getDealer();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
