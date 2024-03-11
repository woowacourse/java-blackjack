
import domain.BlackJackGame;
import domain.Card;
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
        playInitialStep(names, blackJackGame, deck);
        playHitStep(names, blackJackGame, deck);
        playFinalStep(names, blackJackGame);
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

    private static void playInitialStep(List<Name> names, BlackJackGame blackJackGame, Deck deck) {
        OutputView.printInitialStep(names);
        blackJackGame.initialDealing(deck);

        OutputView.printDealerInitialCards(blackJackGame.getDealerCards());
        printPlayersInitialCards(blackJackGame,names);
    }

    private static void printPlayersInitialCards(BlackJackGame blackJackGame, List<Name> names) {
        names.forEach(name -> printInitialPlayerCards(name, blackJackGame));
        OutputView.printNewLine();
    }

    private static void printInitialPlayerCards(Name name, BlackJackGame blackJackGame) {
        List<Card> cards = blackJackGame.getCardsFromName(name);
        OutputView.printPlayerCards(name, cards);
    }

    private static void playHitStep(List<Name> playerNames, BlackJackGame blackJackGame, Deck deck) {
        for(Name playerName : playerNames) {
            hitPlayerStep(playerName, blackJackGame, deck);
        }
        hitDealerStep(blackJackGame, deck);
    }

    private static void hitPlayerStep(Name playerName, BlackJackGame blackJackGame, Deck deck) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (isHittable(playerName, blackJackGame, answer)) {
            answer = InputView.readAnswer(CONSOLE_READER, playerName.name());
            hitByAnswer(answer, playerName, blackJackGame, deck);
            printPlayerHitResult(answer, playerName, blackJackGame, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static boolean isHittable(Name playerName, BlackJackGame blackJackGame, String answer) {
        return !blackJackGame.isBustFromName(playerName) && answer.equals("y");
    }

    private static void hitByAnswer(String answer, Name playerName, BlackJackGame blackJackGame, Deck deck) {
        if (answer.equals("y")) {
            blackJackGame.drawCardFromName(playerName, deck);
        }
    }

    private static void printPlayerHitResult(String answer, Name name, BlackJackGame blackJackGame, boolean isFirstTurn) {
        if (isFirstTurn || answer.equals("y")) {
            List<Card> cards = blackJackGame.getCardsFromName(name);
            OutputView.printPlayerCards(name, cards);
        }
    }

    private static void hitDealerStep(BlackJackGame blackJackGame, Deck deck) {
        int dealerDrawCount = blackJackGame.drawDealerCard(deck);
        OutputView.printDealerHitCount(dealerDrawCount);
    }

    private static void playFinalStep(List<Name> names, BlackJackGame blackJackGame) {
        printDealerStatus(blackJackGame);
        printPlayersStatus(names, blackJackGame);
        OutputView.printGameResult(blackJackGame.getGameResult());
    }

    private static void printDealerStatus(BlackJackGame blackJackGame) {
        List<Card> cards = blackJackGame.getDealerCards();
        int score = blackJackGame.getDealerScore();
        OutputView.printDealerStatus(cards, score);
    }

    private static void printPlayersStatus(List<Name> names, BlackJackGame blackJackGame) {
        for (Name name : names) {
            List<Card> cards = blackJackGame.getCardsFromName(name);
            int score = blackJackGame.getScoreFromName(name);
            OutputView.printPlayerStatus(name, cards, score);
        }
    }
}
