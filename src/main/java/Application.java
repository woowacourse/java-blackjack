import controller.Controller;
import controller.EntityCreator;
import domain.Dealer;
import domain.Players;

public class Application {
    public static void main(String[] args) {
        EntityCreator entityCreator = new EntityCreator();
        Players players = entityCreator.getPlayers();
        Dealer dealer = entityCreator.getDealer();

        Controller controller = new Controller();
        controller.playGame(players, dealer);
    }
}
