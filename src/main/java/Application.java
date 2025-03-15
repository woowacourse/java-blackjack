import console.PlayerRegisterConsole;
import model.Players;

public final class Application {
    public static void main(String[] args) {
        PlayerRegisterConsole playerRegisterConsole = new PlayerRegisterConsole();
        Players players = playerRegisterConsole.registerPlayers();
    }
}
