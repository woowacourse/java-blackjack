package team.blackjack.controller;

import java.util.List;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.GameResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.Player;
import team.blackjack.domain.rule.DefaultBlackjackRule;
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
        initializeBlackjackGame(playerNames);

        DrawResult drawResult = blackJackService.getHandResult();
        OutputView.printDrawResult(drawResult);

        readHitDecision(blackJackService.getPlayer());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);

        GameResult gameResult = blackJackService.getGameResult();
        OutputView.printGameResult(gameResult);
    }

    private List<String> readPlayerNames() {
        OutputView.printPlayerNameRequest();
        return InputView.readPlayerNames();
    }

    private void initializeBlackjackGame(List<String> playerNames) {
        blackJackService.initGame(playerNames);
        blackJackService.dealInitialCards();
    }

    private void readHitDecision(List<Player> players) {
        players.forEach(this::processHit);
    }

    private void processHit(Player player) {
        while (!DefaultBlackjackRule.isBust(player.getScore())) {
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
