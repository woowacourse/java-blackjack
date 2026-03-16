import controller.GameController;
import domain.RandomCardShuffler;
import java.util.Scanner;
import view.InputView;
import view.OutputView;
import view.OutputViewFormatter;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);

        OutputViewFormatter outputViewFormatter = new OutputViewFormatter();
        OutputView outputView = new OutputView(outputViewFormatter);

        GameController gameController = new GameController(inputView, outputView);
        gameController.start(new RandomCardShuffler());
    }
}
