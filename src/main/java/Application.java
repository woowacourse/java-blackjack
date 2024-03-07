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
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        List<Gamer> gamers = inputs.stream()
                .map(Name::new)
                .map(Gamer::new)
                .toList();
        BlackJackGame blackJackGame = new BlackJackGame(gamers);
        blackJackGame.initialDealing();
        GameStatusDto gameInitStatusDto = blackJackGame.getGameStatusDto();
        OutputView.printInitialStatus(gameInitStatusDto);

        gameInitStatusDto.getGamerDtos().stream().forEach(gamer ->
                askForGamer(gamer, blackJackGame));

        blackJackGame.drawDealerCard();

        OutputView.printTotalStatus(blackJackGame.getGameStatusDto());
        OutputView.printGameResult(blackJackGame.getGameResult());
    }

    private static void askForGamer(GamerDto gamer, BlackJackGame blackJackGame) {
        int count = 1;
        while (!blackJackGame.isBustFromName(gamer.getName())) {
            String answer = InputView.readAnswer(CONSOLE_READER, gamer.getName());
            if (answer.equals("n")) {
                if (count == 1) {
                    OutputView.printGamerStatus(gamer);
                }
                return;
            }
            count++;
            blackJackGame.drawCardFromName(gamer.getName());
            OutputView.printGamerStatus(gamer);
        }
    }
}
