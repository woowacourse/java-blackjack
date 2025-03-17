import deck.Deck;
import deck.ShuffledDeckCreator;
import game.BlackjackGame;
import game.BlackjackManager;
import participant.Dealer;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Dealer dealer = new Dealer();
        Players players = new Players(inputView.readNicknames().stream()
                .map(nickname -> new Player(nickname, inputView.readBettingMoney(nickname)))
                .toList());
        Deck deck = new Deck(new ShuffledDeckCreator());
        BlackjackGame blackjackGame = new BlackjackGame();

        BlackjackManager blackjackManager = new BlackjackManager(inputView, outputView, blackjackGame);
        blackjackManager.start(dealer, players, deck);
    }
}
