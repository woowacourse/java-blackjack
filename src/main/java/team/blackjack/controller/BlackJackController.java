package team.blackjack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.PayoutResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.Player;
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
        Map<String, Integer> playerStakes = readPlayerStakes(playerNames);
        initializeBlackjackGame(playerStakes);

        DrawResult drawResult = blackJackService.getHandResult();
        OutputView.printDrawResult(drawResult);

        readHitDecision(blackJackService.getPlayer());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.dealerHit();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);

        PayoutResult payoutResult = blackJackService.getPayoutResult();
        OutputView.printParticipantPayoutResult(payoutResult);
    }

    private List<String> readPlayerNames() {
        OutputView.printPlayerNameRequest();
        return InputView.readPlayerNames();
    }

    private Map<String, Integer> readPlayerStakes(List<String> playerNames) {
        Map<String, Integer> playerStakes = new HashMap<>();

        for (String name : playerNames) {
            OutputView.printPlayerStakeRequest(name);
            playerStakes.put(name, InputView.readPlayerStake());
        }

        return playerStakes;
    }

    private void initializeBlackjackGame(Map<String, Integer> playerStakes) {
        blackJackService.initGame(playerStakes);
        blackJackService.dealInitialCards();
    }

    private void readHitDecision(List<Player> players) {
        players.forEach(this::processHit);
    }

    private void processHit(Player player) {
        while (!player.getHands().getFirst().isBust()) {
            OutputView.printAskDrawCard(player.getName());

            if (!InputView.readHitDecision()) {
                return;
            }

            blackJackService.playerHit(player);
            OutputView.printPlayerCards(player.getName(), player.getHands().getFirst().getCardNames());
        }

        OutputView.printBustMessage();
    }
}
