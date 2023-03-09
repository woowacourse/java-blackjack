import application.BlackJackApplication;
import domain.BlackJackBettingMachine;
import view.InputView;
import view.OutputView;

//TODO: 요구사항 최신화
public class main {

    public static void main(String[] args) {
        BlackJackApplication blackJackApplication = new BlackJackApplication(new InputView(), new OutputView(),
                new BlackJackBettingMachine());
        blackJackApplication.run();
    }
}
