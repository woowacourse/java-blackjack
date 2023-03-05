import blackjackGame.BlackjackGame;
import controller.Controller;
import deck.CardsGenerator;
import deck.Deck;
import deck.ShuffledCardsGenerator;
import player.Dealer;
import player.Players;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        CardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();

        Players players = new Players();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(shuffledCardsGenerator);

        BlackjackGame blackjackGame = new BlackjackGame(players, dealer, deck);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(inputView, outputView, blackjackGame);

        controller.run();
    }
}
