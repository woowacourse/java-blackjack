import domain.BlackJackGame;
import domain.Deck;
import domain.Player;
import domain.Name;
import domain.dto.GameStatusDto;
import domain.dto.GamerDto;
import java.util.List;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class Application {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public static void main(String[] args) {
        Deck deck = new Deck();
        BlackJackGame blackJackGame = setGame();
        List<GamerDto> gamerDtos = showInitialStatus(blackJackGame, deck);
        playMainGame(gamerDtos, blackJackGame,deck);
        showFinalStatus(blackJackGame);
    }

    private static BlackJackGame setGame() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        List<Player> players = inputs.stream()
                .map(Name::new)
                .map(Player::new)
                .toList();
        return new BlackJackGame(players);
    }

    private static List<GamerDto> showInitialStatus(BlackJackGame blackJackGame, Deck deck) {
        blackJackGame.initialDealing(deck);
        GameStatusDto gameInitStatusDto = blackJackGame.getGameStatusDto();
        OutputView.printInitialStatus(gameInitStatusDto);
        return gameInitStatusDto.getGamerDtos();
    }

    private static void playMainGame(List<GamerDto> gamerDtos, BlackJackGame blackJackGame, Deck deck) {
        gamerDtos.forEach(gamer -> askForGamer(gamer, blackJackGame, deck));
        int dealerDrawCount = blackJackGame.drawDealerCard(deck );
        OutputView.printDealerTurn(dealerDrawCount);
    }

    private static void askForGamer(GamerDto gamer, BlackJackGame blackJackGame, Deck deck) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (!blackJackGame.isBustFromName(gamer.getName()) && answer.equals("y")) {
            answer = InputView.readAnswer(CONSOLE_READER, gamer.getName());
            drawIfAnswerIsYes(answer, gamer, blackJackGame, deck);
            printPlayerStatus(answer, gamer, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static void drawIfAnswerIsYes(String answer, GamerDto gamerDto, BlackJackGame blackJackGame, Deck deck) {
        if (answer.equals("y")) {
            blackJackGame.drawCardFromName(gamerDto.getName(), deck);
        }
    }

    private static void printPlayerStatus(String answer, GamerDto gamer, boolean isFirstTurn) {
        if (isFirstTurn || answer.equals("y")) {
            OutputView.printGamerStatus(gamer);
        }
    }

    private static void showFinalStatus(BlackJackGame blackJackGame) {
        OutputView.printTotalStatus(blackJackGame.getGameStatusDto());
        OutputView.printGameResult(blackJackGame.getGameResult());
    }
}
