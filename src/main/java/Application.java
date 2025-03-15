import console.PlayerRegisterConsole;
import controller.Controller;
import model.Players;

public final class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        PlayerRegisterConsole playerRegisterConsole = new PlayerRegisterConsole(controller);
        Players players = playerRegisterConsole.registerPlayers();
    }
}
