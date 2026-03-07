package domain.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroups {
    private List<Player> players;

    public PlayerGroups(List<Player> players) {
        validatePlayerNum(players);
        this.players = new ArrayList<>(players);
    }

    public int getPlayerGroupSize() {
        return players.size();
    }

    private void validatePlayerNum(List<Player> players){
        if (players.size() > 5) {
            throw new IllegalArgumentException();
        }
    }
}
