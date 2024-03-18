import domain.BettingAmount;
import domain.BettingTable;
import domain.BlackJackGame;
import domain.Name;
import domain.Player;
import domain.dto.GameStatus;
import domain.dto.PlayerDto;
import java.util.List;
import java.util.stream.Collectors;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class Application {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public static void main(String[] args) {
        BlackJackGame blackJackGame = setGame();
        BettingTable bettingTable = setBettingTable(blackJackGame);
        List<PlayerDto> playerDtos = showInitialStatus(blackJackGame);
        playMainGame(playerDtos, blackJackGame);
        showFinalProfit(blackJackGame, bettingTable);
    }

    private static BlackJackGame setGame() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        List<Player> players = inputs.stream()
                .map(Name::new)
                .map(Player::new)
                .toList();
        return new BlackJackGame(players);
    }

    private static BettingTable setBettingTable(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        return new BettingTable(players.stream()
                .map(Player::getName)
                .collect(Collectors.toMap(
                        name -> name,
                        Application::getAmountFromName
                )));
    }

    private static BettingAmount getAmountFromName(String playerName) {
        return new BettingAmount(InputView.readBetting(CONSOLE_READER, playerName));
    }

    private static List<PlayerDto> showInitialStatus(BlackJackGame blackJackGame) {
        blackJackGame.initialDealing();
        GameStatus gameInitStatusDto = new GameStatus(blackJackGame.getDealer(), blackJackGame.getPlayers());
        OutputView.printInitialStatus(gameInitStatusDto);
        return gameInitStatusDto.getGamerDtos();
    }

    private static void playMainGame(List<PlayerDto> playerDtos, BlackJackGame blackJackGame) {
        playerDtos.forEach(gamer -> askForGamer(gamer, blackJackGame));
        int dealerDrawCount = blackJackGame.drawDealerCard();
        OutputView.printDealerTurn(dealerDrawCount);
    }

    private static void askForGamer(PlayerDto gamer, BlackJackGame blackJackGame) {
        boolean isFirstTurn = true;
        boolean isDraw = true;
        while (isGamerTurnEnded(blackJackGame, gamer, isDraw)) {
            isDraw = InputView.readAnswer(CONSOLE_READER, gamer.getName());
            drawIfAnswerIsYes(isDraw, gamer, blackJackGame);
            printPlayerStatus(isDraw, gamer, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static boolean isGamerTurnEnded(BlackJackGame blackJackGame, PlayerDto gamer, boolean isDraw) {
        return (!blackJackGame.isBustFromName(gamer.getName()) && isDraw);
    }

    private static void drawIfAnswerIsYes(boolean isDraw, PlayerDto playerDto, BlackJackGame blackJackGame) {
        if (isDraw) {
            blackJackGame.drawCardFromName(playerDto.getName());
        }
    }

    private static void printPlayerStatus(boolean isDraw, PlayerDto gamer, boolean isFirstTurn) {
        if (isFirstTurn || isDraw) {
            OutputView.printGamerStatus(gamer);
        }
    }

    private static void showFinalProfit(BlackJackGame blackJackGame, BettingTable bettingTable) {
        OutputView.printTotalStatus(new GameStatus(blackJackGame.getDealer(), blackJackGame.getPlayers()));
        OutputView.printFinalProfit(
                bettingTable.getDealerProfit(blackJackGame.getGameResult().getPlayersResult()),
                bettingTable.getPlayersProfit(blackJackGame.getGameResult().getPlayersResult()),
                blackJackGame.getPlayers()
        );
    }
}
