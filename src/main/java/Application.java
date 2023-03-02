import controller.Controller;
import domain.RandomCardGenerator;

public final class Application {
    public static void main(String[] args) {
        final Controller controller = new Controller(new RandomCardGenerator());
        controller.run();
    }
}
