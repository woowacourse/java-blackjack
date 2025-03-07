package blackjack.controller;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.domain.GameManager;
import blackjack.domain.Nickname;
import blackjack.domain.PlayerWinningStatistics;
import blackjack.dto.DrawnCardResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public BlackJackController(ApplicationConfiguration configuration) {
        this.inputView = configuration.getInputView();
        this.outputView = configuration.getOutputView();
        this.gameManager = configuration.getGameManager();
    }

    public void run() {
        List<Nickname> nicknames = readNicknames();
        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();

//        List<Player> players = gameManager.getPlayers();
//        Player dealer = new Player(Nickname.createDealerNickname());
//
//        outputView.printCardHeader(players);
//        outputView.printCard(dealer.getNickname(), List.of(gameManager.findCardsByPlayer(dealer).getFirst()));
//
//        for (Player player : players) {
//            List<Card> cards = gameManager.findCardsByPlayer(player);
//
//            outputView.printCard(player.getNickname(), cards);
//        }
//
//        for (Player player : players) {
//            while (!gameManager.isBustPlayer(player) && inputView.readWannaHit(player.getNickname())) {
//                gameManager.hit(player);
//                outputView.printCard(player.getNickname(), gameManager.findCardsByPlayer(player));
//            }
//        }

        printDealerHit();
        printDrawnCardResult();
        printPlayerWinningResults();
    }

    private List<Nickname> readNicknames() {
        return inputView.readNicknames().stream().map(Nickname::new).collect(Collectors.toList());
    }

    private void printDealerHit() {
        int count = gameManager.drawDealerCards();
        outputView.printDealerHit(count);
    }

    private void printDrawnCardResult() {
        List<DrawnCardResult> drawnCardResults = gameManager.calculateDrawnCardResults();
        outputView.printDrawnCardResults(drawnCardResults);
    }

    private void printPlayerWinningResults() {
        PlayerWinningStatistics statistics = gameManager.calculateGameResult();
        outputView.printPlayerWinningResults(statistics);
    }
}
