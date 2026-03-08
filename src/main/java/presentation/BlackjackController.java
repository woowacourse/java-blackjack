package presentation;

import application.BlackjackService;
import application.dto.GameResult;
import domain.card.Card;
import domain.dto.MemberStatus;
import java.util.List;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(BlackjackService blackjackService, InputView inputView, OutputView outputView) {
        this.blackjackService = blackjackService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = getPlayerNames();
        distributeInitialCards();
        playGame(playerNames);
        processDealerTurn();
        finalGameStatus();
        finalGameResult();
    }

    private void finalGameResult() {
        GameResult gameResult = blackjackService.getGameResults();
        outputView.printGameResult(gameResult);
    }

    private void finalGameStatus() {
        List<MemberStatus> statuses = blackjackService.getMemberStatuses();
        outputView.printFinalMemberStatus(statuses);
    }

    private void processDealerTurn() {
        boolean hasDrawn = blackjackService.drawDealerCardIfAvailable();
        if (hasDrawn) {
            outputView.printDealerDrawResult();
        }
    }

    private void playGame(List<String> playerNames) {
        for (String playerName : playerNames) {
            playAllRoundOfPlayer(playerName);
        }
    }

    private void distributeInitialCards() {
        List<MemberStatus> memberStatuses = blackjackService.getMemberStatuses();
        outputView.printInitialStatus(memberStatuses);
    }

    private List<String> getPlayerNames() {
        List<String> playerNames = inputView.readPlayerNames();
        blackjackService.joinPlayerToGame(playerNames);
        return playerNames;
    }

    private void playAllRoundOfPlayer(String playerName) {
        while (blackjackService.isContinuable(playerName) && inputView.playContinue(playerName)) {
            List<Card> cards = blackjackService.startOneRound(playerName);
            outputView.printCurrentCard(playerName, cards);
        }
    }
}
