import domain.BlackjackService;
import domain.Name;
import domain.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, players);

        play(blackjackService);
    }

    private void play(BlackjackService blackjackService) {
        startSetting(blackjackService);
        proceedPlayerTurn(blackjackService);
        proceedDealerTurn(blackjackService);
        handleResult(blackjackService);
    }

    private void startSetting(BlackjackService blackjackService) {
        blackjackService.initialDistribute();
        outputView.printSetting(blackjackService.getDealer(), blackjackService.getPlayers());
    }

    private void proceedPlayerTurn(BlackjackService blackjackService) {
        for (int playerIndex = 0; playerIndex < blackjackService.countPlayers(); playerIndex++) {
            proceedOnePlayerTurn(blackjackService, playerIndex);
        }
    }

    private void proceedDealerTurn(BlackjackService blackjackService) {
        while (blackjackService.IsDealerNotOver()) {
            blackjackService.addCardToDealer();
            outputView.printDealerOneMoreCard();
        }
    }

    private void proceedOnePlayerTurn(BlackjackService blackjackService, int playerIndex) {
        while (blackjackService.isPlayerNotOver(playerIndex) &&
                inputView.readCommand(blackjackService.getPlayerName(playerIndex).getName())) {
            blackjackService.addCardToPlayer(playerIndex);
            Player player = blackjackService.getPlayer(playerIndex);
            outputView.printCurrentCard(player);
            outputView.printNewLine();
        }
    }

    private void handleResult(BlackjackService blackjackService) {
        outputView.printResult(blackjackService.getDealer(), blackjackService.getPlayers());
        outputView.printVictory(blackjackService.calculateVictory());
    }
}
