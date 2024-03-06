import domain.Name;
import domain.BlackjackService;
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
        blackjackService.initialDistribute();
        outputView.printSetting(dealer, players);

        players.forEach((player) -> {

        });

        for (int playerIndex = 0; playerIndex < blackjackService.countPlayers(); playerIndex++) {
            while (blackjackService.isPlayerNotOver(playerIndex) &&
                    inputView.readCommand(blackjackService.getPlayerName(playerIndex).getName())) {
                blackjackService.addCardToPlayer(playerIndex);
                Player player = blackjackService.getPlayer(playerIndex);
                outputView.printCurrentCard(player);
                outputView.printNewLine();
            }
        }

        while (blackjackService.IsDealerNotOver()) {
            blackjackService.addCardToDealer();
            outputView.printDealerOneMoreCard();
        }

        outputView.printResult(dealer, players);
        outputView.printVictory(blackjackService.calculateVictory());
    }
}
