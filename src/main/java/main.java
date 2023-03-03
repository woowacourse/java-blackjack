import application.BlackJackApplication;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class main {

    public static void main(String[] args) {
        BlackJackApplication blackJackApplication = new BlackJackApplication(new InputView(), new OutputView(), new BlackJackService());
        blackJackApplication.run();
    }
}
