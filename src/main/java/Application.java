import controller.Controller;
import controller.EntityCreator;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        EntityCreator entityCreator = new EntityCreator();

        controller.playGame(entityCreator.getPlayers(), entityCreator.getDealer());
    }
}
