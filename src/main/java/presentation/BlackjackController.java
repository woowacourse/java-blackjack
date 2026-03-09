package presentation;

import application.BlackjackService;
import application.dto.RoundResult;
import domain.dto.GameResult;
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

    public void executeGame() {
        List<String> playerNames = getPlayerNames();
        distributeInitialCards();
        playGame(playerNames);
        checkDrawableOfDealer();
        finalGameStatus();
        finalGameResult();
    }

    private void finalGameResult() {
        List<GameResult> gameResults = blackjackService.getGameResults();
        outputView.printGameResult(gameResults);
    }

    private void finalGameStatus() {
        List<MemberStatus> statuses = blackjackService.getMemberStatuses();
        outputView.printFinalMemberStatus(statuses);
    }

    private void checkDrawableOfDealer() {
        boolean dealerDrawable = blackjackService.checkDealerDrawable();
        if (dealerDrawable) {
            outputView.printDealerDrawOut();
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
        boolean isBust = false;
        while (!isBust && inputView.playContinue(playerName)) {
            RoundResult roundResult = blackjackService.startOneRound(playerName);
            outputView.printCurrentCard(playerName, roundResult);
            isBust = roundResult.isBust();
        }
    }
}
