import controller.BlackjackController;
import domain.card.Card;
import domain.card.CardPack;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView(new Scanner(System.in));
        final OutputView outputView = new OutputView(System.out);

        final CardPack cardPack = new CardPack(Card.allCards());
        cardPack.shuffle();

        BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.play(cardPack);
    }
}
