import application.BlackJackApplication;
import domain.BlackJackBettingMachine;
import view.InputView;
import view.OutputView;

public class main {

    public static void main(String[] args) {
        BlackJackApplication blackJackApplication = new BlackJackApplication(new InputView(), new OutputView(),
                new BlackJackBettingMachine());
        blackJackApplication.run();
    }
}
