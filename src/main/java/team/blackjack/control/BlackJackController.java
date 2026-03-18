package team.blackjack.control;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import team.blackjack.service.BlackjackService;
import team.blackjack.view.InputView;
import team.blackjack.view.OutputView;

public class BlackJackController {
    public static final String YES_INPUT = "y";
    public static final String NO_INPUT = "n";

    private final BlackjackService blackJackService;

    public BlackJackController(BlackjackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {

        final LinkedHashSet<String> playerNames = readPlayerNames();
        playerNames.forEach(blackJackService::addPlayer);

        blackJackService.initParticipantCard();

        readAllPlayerBattingMoneyRetry(playerNames);
        OutputView.printDrawResult(blackJackService.getDrawResult());

        hitAllPlayerUntilStand(playerNames);
        hitDealerUntilStand();

        OutputView.printParticipantScoreResult(blackJackService.getParticipantScoreResult());
        OutputView.printRevenueResult(blackJackService.getRevenueResult());
    }

    private void hitDealerUntilStand() {
        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }
    }

    private void readAllPlayerBattingMoneyRetry(Set<String> playerNames) {
       playerNames.forEach(name -> {
           int battingMoney = readPlayerBattingMoneyUntilPositive(name);
           blackJackService.batMoney(name, battingMoney);
       });
    }

    private int readPlayerBattingMoneyUntilPositive(String playerName) {
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

    private LinkedHashSet<String> readPlayerNames(){
        OutputView.printPlayerNameRequest();
        List<String> playerNames = InputView.readPlayerNames();

        while (hasDuplicatedName(playerNames)) {
            OutputView.printDuplicatedNameMessage();
            OutputView.printPlayerNameRequest();
            playerNames = InputView.readPlayerNames();
        }

        return new LinkedHashSet<>(playerNames);
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != new HashSet<>(playerNames).size();
    }

    private void hitAllPlayerUntilStand(Set<String> playerNames) {
            playerNames.forEach(this::hitPlayerUntilStand);
    }

    private void hitPlayerUntilStand(String playerName) {
        while (isPlayerInputHit(playerName)) {
            blackJackService.hitPlayer(playerName);
            OutputView.printPlayerCards(playerName, blackJackService.getPlayerCardNames(playerName));
        }
    }

    private boolean isPlayerInputHit(String playerName) {
        if (!blackJackService.canPlayerHit(playerName)) {
            OutputView.printBustMessage();
            return false;
        }

        OutputView.printAskDrawCard(playerName);
        String hitYn;
        do {
            hitYn = InputView.readHitDecision();
        } while (!validateDecisionInput(hitYn));

        return YES_INPUT.equalsIgnoreCase(hitYn);
    }

     private boolean validateDecisionInput(String input) {
        if (!YES_INPUT.equalsIgnoreCase(input) && !NO_INPUT.equalsIgnoreCase(input)) {
            OutputView.printWrongDecisionInputMessage();
            return false;
        }

        return true;
    }

}
