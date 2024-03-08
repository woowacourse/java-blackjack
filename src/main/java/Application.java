import domain.BlackJackGame;
import domain.Gamer;
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
        GameStatusDto gameInitStatusDto = blackJackGame.getGameStatusDto();
        OutputView.printInitialStatus(gameInitStatusDto);
        return gameInitStatusDto.getGamerDtos();
    }

    private static void playMainGame(List<GamerDto> gamerDtos, BlackJackGame blackJackGame) {
        gamerDtos.stream().forEach(gamer ->
                askForGamer(gamer, blackJackGame));
        blackJackGame.drawDealerCard();
    }

    private static void askForGamer(GamerDto gamer, BlackJackGame blackJackGame) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (!blackJackGame.isBustFromName(gamer.getName()) && answer.equals("y")) {
            answer = InputView.readAnswer(CONSOLE_READER, gamer.getName());
            drawIfAnswerIsYes(answer, gamer, blackJackGame);
            printPlayerStatus(answer, gamer, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static void drawIfAnswerIsYes(String answer, GamerDto gamerDto, BlackJackGame blackJackGame) {
        if (answer.equals("y")) {
            blackJackGame.drawCardFromName(gamerDto.getName());
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
