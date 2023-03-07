import controller.Controller;
import controller.EntityCreator;

public class Application {
    public static void main(String[] args) {
        EntityCreator entityCreator = new EntityCreator();

        Controller controller = new Controller(entityCreator.getPlayers(), entityCreator.getDealer());
        controller.playGame();
    }
}
