import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackConsole blackjackConsole = new BlackjackConsole(new OutputView(), new InputView());
        blackjackConsole.run();
    }
}
