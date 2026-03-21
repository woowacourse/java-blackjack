import domain.Deck;
import domain.GameManager;
import domain.RandomDeckShuffler;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        Deck deck = Deck.create();
        deck = deck.shuffle(new RandomDeckShuffler());
        GameManager gameManager = GameManager.createWith(deck);

        GamePlayer gamePlayer = new GamePlayer(inputView, outputView, gameManager);

        gamePlayer.run();
    }
}
