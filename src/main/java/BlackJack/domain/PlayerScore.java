package BlackJack.domain;

import BlackJack.domain.User.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerScore {
    private Map<String, Result> playersScore;

    public PlayerScore(){
        this.playersScore = new HashMap<>();
    }

    public void addResult(Player player, Result result){
        playersScore.put(player.getName(), result);
    }

    public Map<String, Result> getPlayersScore() {
        return playersScore;
    }

    public Map<String, Result> getPlayersResult() {
        return Collections.unmodifiableMap(playersScore);
    }

}
