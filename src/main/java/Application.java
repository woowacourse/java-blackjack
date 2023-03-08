import controller.Controller;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(String[] args) {
        final Controller controller = new Controller(new InputView(), new OutputView());
        controller.run();
    }
}
