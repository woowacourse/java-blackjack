import player.Name;
import player.Player;
import player.Players;

public class BlackjackGame {
    private final Players players = new Players();


    public void addPlayer(String playerName) {
        players.add(new Player(new Name(playerName)));
    }
}
