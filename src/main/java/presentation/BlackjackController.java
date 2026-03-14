package presentation;

import application.BlackjackService;
import dto.RoundResult;
import dto.GameResult;
import dto.MemberStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import presentation.ui.BlackjackView;

public class BlackjackController {

    private final BlackjackService blackjackService;
    private final BlackjackView blackjackView;

    public BlackjackController(BlackjackService blackjackService, BlackjackView blackjackView) {
        this.blackjackService = blackjackService;
        this.blackjackView = blackjackView;
    }

    public void executeGame() {
        List<String> playerNames = blackjackView.inputView().readPlayerNames();
        setUpGame(playerNames);
        playGame(playerNames);
        checkDrawableOfDealer();
        finalGameStatus();
        finalGameResult();
    }

    private void finalGameResult() {
        List<GameResult> gameResults = blackjackService.getGameResults();
        blackjackView.outputView().printGameResult(gameResults);
    }

    private void finalGameStatus() {
        List<MemberStatus> statuses = blackjackService.getMemberStatuses();
        blackjackView.outputView().printFinalMemberStatus(statuses);
    }

    private void checkDrawableOfDealer() {
        boolean dealerDrawable = blackjackService.checkDealerDrawable();
        if (dealerDrawable) {
            blackjackView.outputView().printDealerDrawOut();
        }
    }

    private void playGame(List<String> playerNames) {
        for (String playerName : playerNames) {
            playAllRoundOfPlayer(playerName);
        }
    }

    private void setUpGame(List<String> playerNames) {
        Map<String, Integer> playerBets = new HashMap<>();
        for (String playerName : playerNames) {
            int betAmount = blackjackView.inputView().readPlayerBetAmount(playerName);
            playerBets.put(playerName, betAmount);
        }
        blackjackService.initializeGame(playerBets);
        List<MemberStatus> memberStatuses = blackjackService.getMemberStatuses();
        blackjackView.outputView().printInitialStatus(memberStatuses);
    }

    private void playAllRoundOfPlayer(String playerName) {
        while (!blackjackService.isFinishedByName(playerName) && blackjackView.inputView().playContinue(playerName)) {
            RoundResult roundResult = blackjackService.startOneRound(playerName);
            blackjackView.outputView().printCurrentCard(playerName, roundResult);
        }
        blackjackService.endPlayerRound(playerName);
    }
}
