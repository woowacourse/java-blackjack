package team.blackjack.control;

import java.util.List;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.RevenueResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.service.BlackJackService;
import team.blackjack.view.InputView;
import team.blackjack.view.OutputView;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        List<String> playerNames = readPlayerNames();
        blackJackService.initGame(playerNames);
        blackJackService.drawInitialCards();

        readAllPlayerBattingMoneyRetry(blackJackService.getAllPlayerNames());

        DrawResult drawResult = blackJackService.getDrawResult();
        OutputView.printDrawResult(drawResult);

        readPlayerHitDecision(blackJackService.getAllPlayerNames());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);

        RevenueResult revenueResult = blackJackService.getRevenueResult();
        OutputView.printRevenueResult(revenueResult);
    }

    public void readAllPlayerBattingMoneyRetry(List<String> playerNames) {
       playerNames.forEach(playerName -> {
           int battingMoney = readPlayerBattingMoneyRetry(playerName);
           blackJackService.batMoney(playerName, battingMoney);
       });
    }

    private int readPlayerBattingMoneyRetry(String playerName) {
        int battingMoney;

        do {
            battingMoney = readPlayerBattingMoney(playerName);
        } while (battingMoney <= 0);

        return battingMoney;
    }

    private int readPlayerBattingMoney(String playerName) {
        int battingMoney = -1;
        OutputView.printAskBettingMoney(playerName);
        try {
            battingMoney = InputView.readPlayerBattingMoney();
        } catch (NumberFormatException e) {
            OutputView.printWrongDecisionInputMessage();
        }

        return battingMoney;
    }

    private List<String> readPlayerNames(){
        OutputView.printPlayerNameRequest();
        List<String> playerNames = InputView.readPlayerNames();

        while (hasDuplicatedName(playerNames)) {
            OutputView.printDuplicatedNameMessage();
            OutputView.printPlayerNameRequest();
            playerNames = InputView.readPlayerNames();
        }
        return playerNames;
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != playerNames.stream().distinct().count();
    }

    private void readPlayerHitDecision(List<String> playerNames) {
        playerNames.forEach(this::processPlayerHit);
    }

    private void processPlayerHit(String playerName) {
        while (readPlayerHitDecision(playerName)) {
            blackJackService.hitPlayer(playerName);
            OutputView.printPlayerCards(playerName, blackJackService.findPlayerCardNamesByName(playerName));
        }
    }

    private boolean readPlayerHitDecision(String playerName) {
        final boolean isPlayerBust = blackJackService.isPlayerBust(playerName);

        if (isPlayerBust) {
            OutputView.printBustMessage();
            return false;
        }

        OutputView.printAskDrawCard(playerName);
        String hitYn = InputView.readHitDecision();

        while (!"y".equalsIgnoreCase(hitYn) && !"n".equalsIgnoreCase(hitYn)) {
            OutputView.printWrongDecisionInputMessage();
            hitYn = InputView.readHitDecision();
        }

        return "y".equalsIgnoreCase(hitYn);
    }
}
