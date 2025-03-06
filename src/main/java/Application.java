import controller.BlackjackApplication;
import domain.PlayerRepository;
import view.InputView;
import view.OutputView;
import view.support.InputParser;
import view.support.OutputFormatter;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new InputParser());
        OutputView outputView = new OutputView(new OutputFormatter());
        PlayerRepository playerRepository = PlayerRepository.getInstance();
        BlackjackApplication blackjackApplication = new BlackjackApplication(inputView, outputView, playerRepository);
        blackjackApplication.execute();
    }
}
