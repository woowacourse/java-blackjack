import java.util.ArrayList;
import java.util.List;

public class Players {
    List<Player> players = new ArrayList<>();


    public void add(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
