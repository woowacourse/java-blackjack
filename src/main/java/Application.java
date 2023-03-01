import controller.Controller;
import domain.RandomCardGenerator;

public class Application {
    public static void main(String[] args) {
        final Controller controller = new Controller(new RandomCardGenerator());
        controller.run();
    }
}
