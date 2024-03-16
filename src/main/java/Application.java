import controller.gamestatus.GameStatus;
import controller.gamestatus.InitialGame;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import java.util.List;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class Application {
    
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public static void main(String[] args) {
        Deck deck = Deck.withFullCards();
        List<Name> names = setPlayerNames();
        Players players = setPlayers(names);
        Dealer dealer = new Dealer();
        playGame(dealer, players, deck);
    }

    private static List<Name> setPlayerNames() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        return inputs.stream()
                .map(Name::new)
                .toList();
    }

    private static Players setPlayers(List<Name> names) {
        List<Player> players = names.stream()
                .map(Application::setBettingMoney)
                .toList();
        return new Players(players);
    }

    private static Player setBettingMoney(Name name) {
        double input = InputView.readBettingMoney(CONSOLE_READER, name.name());
        OutputView.printNewLine();
        return new Player(name, new Money(input));
    }

    private static void playGame(Dealer dealer, Players players, Deck deck) {
        GameStatus gameStatus = new InitialGame();
        while (gameStatus.isPlayable()) {
            gameStatus = gameStatus.play(dealer, players, deck);
        }
    }
}
