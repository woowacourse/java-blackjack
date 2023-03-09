import controller.Runner;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(String[] args) {
        final Runner runner = new Runner(new InputView(), new OutputView());
        runner.run();
    }
}
