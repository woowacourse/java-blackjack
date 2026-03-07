import controller.BlackjackController;
import domain.RandomValueGenerator;
import domain.RandomValueGeneratorImpl;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args){
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        RandomValueGenerator randomValueGenerator = new RandomValueGeneratorImpl();
        BlackjackController controller = new BlackjackController(inputView,outputView,randomValueGenerator);

        controller.start();
    }
}
