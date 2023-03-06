import application.BlackJackApplication;
import view.InputView;
import view.OutputView;

public class main {

    public static void main(String[] args) {
        BlackJackApplication blackJackApplication = new BlackJackApplication(new InputView(), new OutputView());
        blackJackApplication.run();
    }
}
