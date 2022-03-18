package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerScore {
    private Map<String, Result> playersScore;

    public PlayerScore() {
        this.playersScore = new HashMap<>();
    }

    public void addResult(Player player, Result result) {
        playersScore.put(player.getName(), result);
    }


    public void makePlayerResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
           this.addResult(player, Result.judge(dealer,player));
        }
    }

    public Map<String, Result> getPlayersScore() {
        return playersScore;
    }

    public Map<String, Result> getPlayersResult() {
        return Collections.unmodifiableMap(playersScore);
    }

    public void makeBlackjackResult(Players players) {
        for (Player player : players.getPlayers()) {
            addResult(player,player.judgeByBlackjack());
        }
    }
}
