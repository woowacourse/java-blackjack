import controller.BlackjackApplication;
import domain.ParticipantRepository;
import view.InputView;
import view.OutputView;
import view.support.InputParser;
import view.support.OutputFormatter;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new InputParser());
        OutputView outputView = new OutputView(new OutputFormatter());
        ParticipantRepository participantRepository = ParticipantRepository.getInstance();
        BlackjackApplication blackjackApplication = new BlackjackApplication(inputView, outputView, participantRepository);
        blackjackApplication.execute();
    }
}
