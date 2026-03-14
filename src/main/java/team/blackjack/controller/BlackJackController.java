package team.blackjack.controller;

import java.util.ArrayList;
import java.util.List;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.PayoutResult;
import team.blackjack.service.dto.PlayerRequest;
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
        List<PlayerRequest> playerRequests = readPlayerStakes(playerNames);
        initializeBlackjackGame(playerRequests);

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

    private List<PlayerRequest> readPlayerStakes(List<String> playerNames) {
        List<PlayerRequest> playerRequests = new ArrayList<>();

        for (String name : playerNames) {
            OutputView.printPlayerStakeRequest(name);
            playerRequests.add(new PlayerRequest(name, InputView.readPlayerStake()));
        }

        return playerRequests;
    }

    private void initializeBlackjackGame(List<PlayerRequest> playerRequests) {
        blackJackService.initGame(playerRequests);
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
