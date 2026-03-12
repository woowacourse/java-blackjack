import controller.GameController;
import domain.Deck;
import domain.GameManager;
import java.util.Collections;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        Deck deck = Deck.create();
        deck.shuffle(Collections::shuffle);
        GameManager gameManager = GameManager.createWith(deck);

        GameController gameController = new GameController(inputView, outputView, gameManager);

        gameController.run();
    }
}
