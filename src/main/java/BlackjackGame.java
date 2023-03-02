import player.Name;
import player.Player;
import player.Players;

public class BlackjackGame {
    private final Players players;

    public BlackjackGame(Players players) {
        this.players = players;
    }

    public void addPlayer(String playerName) {
        players.add(new Player(new Name(playerName)));
    }
}
