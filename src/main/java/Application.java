import domain.BlackJackGame;
import domain.Deck;
import domain.Player;
import domain.Name;
import java.util.List;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class Application {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public static void main(String[] args) {
        Deck deck = Deck.withFullCards();
        List<Name> names = setPlayerNames();
        BlackJackGame blackJackGame = setGame(names);
        setInitialGame(names, blackJackGame, deck);
        playMainGame(names, blackJackGame, deck);
        showFinalStatus(names, blackJackGame);
    }

    private static List<Name> setPlayerNames() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        return inputs.stream()
                    .map(Name::new)
                    .toList();
    }

    private static BlackJackGame setGame(List<Name> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new BlackJackGame(players);
    }

    private static void setInitialGame(List<Name> names, BlackJackGame blackJackGame, Deck deck) {
        OutputView.printInitialGame(names);
        blackJackGame.initialDealing(deck);
        OutputView.printDealerInitialCards(blackJackGame.getDealerCards());
        printPlayersCards(blackJackGame,names);
    }

    private static void printPlayersCards(BlackJackGame blackJackGame, List<Name> names) {
        names.forEach(name -> OutputView.printPlayerCards(name, blackJackGame.getCardsFromName(name.name())));
        OutputView.printNewLine();
    }

    private static void playMainGame(List<Name> playerNames, BlackJackGame blackJackGame, Deck deck) {
        playerNames.forEach(playerName -> askForGamer(playerName, blackJackGame, deck));
        int dealerDrawCount = blackJackGame.drawDealerCard(deck);
        OutputView.printDealerTurn(dealerDrawCount);
    }

    private static void askForGamer(Name playerName, BlackJackGame blackJackGame, Deck deck) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (!blackJackGame.isBustFromName(playerName.name()) && answer.equals("y")) {
            answer = InputView.readAnswer(CONSOLE_READER, playerName.name());
            drawIfAnswerIsYes(answer, playerName, blackJackGame, deck);
            printPlayerStatus(answer, playerName, blackJackGame, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static void drawIfAnswerIsYes(String answer, Name playerName, BlackJackGame blackJackGame, Deck deck) {
        if (answer.equals("y")) {
            blackJackGame.drawCardFromName(playerName.name(), deck);
        }
    }

    private static void printPlayerStatus(String answer, Name playerName, BlackJackGame blackJackGame, boolean isFirstTurn) {
        if (isFirstTurn || answer.equals("y")) {
            OutputView.printPlayerCards(playerName,blackJackGame.getCardsFromName(playerName.name()));
        }
    }

    private static void showFinalStatus(List<Name> names, BlackJackGame blackJackGame) {
        OutputView.printDealerStatus(blackJackGame.getDealerCards(),blackJackGame.getDealerScore());
        names.forEach(name -> OutputView.printPlayerStatus(name
                ,blackJackGame.getCardsFromName(name.name()),
                blackJackGame.getScoreFromName(name.name())));
        OutputView.printGameResult(blackJackGame.getGameResult());
    }
}
