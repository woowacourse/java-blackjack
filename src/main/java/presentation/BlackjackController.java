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

    private final BlackjackView blackjackView;

    public BlackjackController(BlackjackView blackjackView) {
        this.blackjackView = blackjackView;
    }

    public void executeGame() {
        List<String> playerNames = blackjackView.inputView().readPlayerNames();
        BlackjackService blackjackService = new BlackjackService(readPlayerInitStatus(playerNames));
        setUpGame(blackjackService);
        playGame(blackjackService, playerNames);
        checkDrawableOfDealer(blackjackService);
        finalGameStatus(blackjackService);
        finalGameResult(blackjackService);
    }

    private void finalGameResult(BlackjackService blackjackService) {
        List<GameResult> gameResults = blackjackService.getGameResults();
        blackjackView.outputView().printGameResult(gameResults);
    }

    private void finalGameStatus(BlackjackService blackjackService) {
        List<MemberStatus> statuses = blackjackService.getMemberStatuses();
        blackjackView.outputView().printFinalMemberStatus(statuses);
    }

    private void checkDrawableOfDealer(BlackjackService blackjackService) {
        boolean dealerDrawable = blackjackService.checkDealerDrawable();
        if (dealerDrawable) {
            blackjackView.outputView().printDealerDrawOut();
        }
    }

    private void playGame(BlackjackService blackjackService, List<String> playerNames) {
        for (String playerName : playerNames) {
            playAllRoundOfPlayer(blackjackService, playerName);
        }
    }

    private Map<String, Integer> readPlayerInitStatus(List<String> playerNames) {
        Map<String, Integer> playerBets = new HashMap<>();
        for (String playerName : playerNames) {
            int betAmount = blackjackView.inputView().readPlayerBetAmount(playerName);
            playerBets.put(playerName, betAmount);
        }
        return playerBets;
    }

    private void setUpGame(BlackjackService blackjackService) {
        List<MemberStatus> memberStatuses = blackjackService.getMemberStatuses();
        blackjackView.outputView().printInitialStatus(memberStatuses);
    }

    private void playAllRoundOfPlayer(BlackjackService blackjackService, String playerName) {
        while (!blackjackService.isFinishedByName(playerName) && blackjackView.inputView().playContinue(playerName)) {
            RoundResult roundResult = blackjackService.startOneRound(playerName);
            blackjackView.outputView().printCurrentCard(playerName, roundResult);
        }
        blackjackService.endPlayerRound(playerName);
    }
}
