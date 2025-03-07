package blackjack.controller;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.domain.GameManager;
import blackjack.domain.Nickname;
import blackjack.domain.Player;
import blackjack.dto.HandsAfterDrawingCard;
import blackjack.dto.HandsBeforeDrawingCard;
import blackjack.dto.PlayerWinningStatistics;
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

        printHandsBeforeDrawingCard();

        processHit();

        printDealerHit();
        printDrawnCardResult();
        printPlayerWinningResults();
    }

    private List<Nickname> readNicknames() {
        return inputView.readNicknames().stream().map(Nickname::new).collect(Collectors.toList());
    }

    private void processHit() {
        List<Player> players = gameManager.getPlayers();
        for (Player player : players) {
            while (!gameManager.isBustPlayer(player) && inputView.readWannaHit(player.getNickname())) {
                gameManager.hit(player);
                outputView.printCard(player.getNickname(), gameManager.findCardsByPlayer(player));
            }
        }
    }

    private void printHandsBeforeDrawingCard() {
        HandsBeforeDrawingCard hands = gameManager.getHandBeforeDrawCard();
        outputView.printHandsBeforeDrawingCard(hands);
    }

    private void printDealerHit() {
        int count = gameManager.drawDealerCards();
        outputView.printDealerHit(count);
    }

    private void printDrawnCardResult() {
        HandsAfterDrawingCard handAfterDrawCard = gameManager.getHandAfterDrawCard();
        outputView.printHandsAfterDrawingCard(handAfterDrawCard);
    }

    private void printPlayerWinningResults() {
        PlayerWinningStatistics statistics = gameManager.calculateGameResult();
        outputView.printPlayerWinningResults(statistics);
    }
}
