import controller.Controller;
import domain.card.RandomCardGenerator;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(String[] args) {
        final Controller controller = new Controller(new InputView(), new OutputView());
        controller.run();
    }
}
