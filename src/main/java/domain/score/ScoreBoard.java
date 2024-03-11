package domain.score;

import domain.player.Bet;
import domain.player.Name;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private final Score dealerScore = new Score();
    private final Map<Name, Bet> playerScore;

    public ScoreBoard(Map<Name, Bet> playerScore) {
        this.playerScore = new HashMap<>(playerScore);
    }

    public void updatePlayerScore(Name name, Status status) {
        Bet bet = playerScore.get(name);
        Bet revenue = status.calculateRevenue(bet);
        playerScore.put(name, revenue);
    }

    public void updateDealerScore(Status status) {
        dealerScore.increaseScore(status);
    }

    public Map<Name, Bet> getPlayerScore() {
        return Collections.unmodifiableMap(playerScore);
    }

    public Score getDealerScore() {
        return dealerScore;
    }
}
