package blackjack.controller;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.domain.GameManager;
import blackjack.domain.Nickname;
import blackjack.dto.HandState;
import blackjack.dto.HandsAfterDrawingCard;
import blackjack.dto.HandsBeforeDrawingCard;
import blackjack.dto.PlayerWinningStatistics;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public BlackJackController(ApplicationConfiguration configuration) {
        this.inputView = configuration.getInputView();
        this.outputView = configuration.getOutputView();
        this.gameManager = configuration.getGameManager();
        this.gameManager.initialize(this::readWannaHit, this::printHitResult);
    }

    public void run() {
        startGame();
        startHit();
        startDealerDrawing();
        printGameResult();
    }

    private void startGame() {
        List<Nickname> nicknames = readNicknames();
        HandsBeforeDrawingCard handsBeforeDrawingCard = gameManager.startGame(nicknames);
        outputView.printHandsBeforeDrawingCard(handsBeforeDrawingCard);
    }

    private void startHit() {
        gameManager.processHit();
    }

    private void startDealerDrawing() {
        int count = gameManager.drawDealerCards();
        outputView.printDealerHit(count);
    }

    private void printGameResult() {
        HandsAfterDrawingCard handAfterDrawCard = gameManager.getHandAfterDrawCard();
        outputView.printHandsAfterDrawingCard(handAfterDrawCard);
        PlayerWinningStatistics statistics = gameManager.calculateGameResult();
        outputView.printPlayerWinningResults(statistics);
    }

    private List<Nickname> readNicknames() {
        List<String> inputs = inputView.readNicknames();
        return inputs.stream()
                .map(Nickname::new)
                .toList();
    }

    private boolean readWannaHit(Nickname nickname) {
        return inputView.readWannaHit(nickname);
    }

    private void printHitResult(HandState state) {
        outputView.printCard(state.nickname(), state.cards());
    }
}
