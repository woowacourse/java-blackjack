import controller.Controller;
import controller.EntityCreator;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        EntityCreator entityCreator = new EntityCreator();

        controller.startGame(entityCreator.getPlayers(), entityCreator.getDealer());
    }
}
