import domain.BlackJackGame;
import domain.Gamer;
import domain.Name;
import domain.dto.GameStatus;
import domain.dto.GamerDto;
import java.util.List;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class Application {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public static void main(String[] args) {
        BlackJackGame blackJackGame = setGame();
        List<GamerDto> gamerDtos = showInitialStatus(blackJackGame);
        playMainGame(gamerDtos, blackJackGame);
        showFinalStatus(blackJackGame);
    }

    private static BlackJackGame setGame() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        List<Gamer> gamers = inputs.stream()
                .map(Name::new)
                .map(Gamer::new)
                .toList();
        return new BlackJackGame(gamers);
    }

    private static List<GamerDto> showInitialStatus(BlackJackGame blackJackGame) {
        blackJackGame.initialDealing();
        GameStatus gameInitStatusDto = blackJackGame.getGameStatusDto();
        OutputView.printInitialStatus(gameInitStatusDto);
        return gameInitStatusDto.getGamerDtos();
    }

    private static void playMainGame(List<GamerDto> gamerDtos, BlackJackGame blackJackGame) {
        gamerDtos.forEach(gamer -> askForGamer(gamer, blackJackGame));
        int dealerDrawCount = blackJackGame.drawDealerCard();
        OutputView.printDealerTurn(dealerDrawCount);
    }

    private static void askForGamer(GamerDto gamer, BlackJackGame blackJackGame) {
        boolean isFirstTurn = true;
        boolean isDraw = true;
        while (isGamerTurnEnded(blackJackGame, gamer, isDraw)) {
            isDraw = InputView.readAnswer(CONSOLE_READER, gamer.getName());
            drawIfAnswerIsYes(isDraw, gamer, blackJackGame);
            printPlayerStatus(isDraw, gamer, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static boolean isGamerTurnEnded(BlackJackGame blackJackGame, GamerDto gamer, boolean isDraw) {
        return (!blackJackGame.isBustFromName(gamer.getName()) && isDraw);
    }

    private static void drawIfAnswerIsYes(boolean isDraw, GamerDto gamerDto, BlackJackGame blackJackGame) {
        if (isDraw) {
            blackJackGame.drawCardFromName(gamerDto.getName());
        }
    }

    private static void printPlayerStatus(boolean isDraw, GamerDto gamer, boolean isFirstTurn) {
        if (isFirstTurn || isDraw) {
            OutputView.printGamerStatus(gamer);
        }
    }

    private static void showFinalStatus(BlackJackGame blackJackGame) {
        OutputView.printTotalStatus(blackJackGame.getGameStatusDto());
        OutputView.printGameResult(blackJackGame.getGameResult());
    }
}
