import controller.BlackJackController;
import domain.CardCreationStrategy;
import domain.RandomCardCreationStrategy;
import view.InputViewImpl;
import view.OutputViewImpl;

public class Main {
    public static void main(String[] args) {
        InputViewImpl inputViewImpl = new InputViewImpl();
        OutputViewImpl outputViewImpl = new OutputViewImpl();
        CardCreationStrategy strategy = new RandomCardCreationStrategy();

        new BlackJackController(inputViewImpl, outputViewImpl, strategy).doGameProcess();
    }
}
