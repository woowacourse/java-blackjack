package team.blackjack.controler;

import java.util.List;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.GameResult;
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

        DrawResult drawResult = blackJackService.getDrawResult();
        OutputView.printDrawResult(drawResult);

        readHitDecision(blackJackService.getPlayerNames());

        while (blackJackService.shouldDealerHit()) {
            OutputView.printDealerHitMessage();
            blackJackService.hitDealer();
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        OutputView.printParticipantScoreResult(scoreResult);

        GameResult gameResult = blackJackService.getGameResult();
        OutputView.printGameResult(gameResult);
    }

    private List<String> readPlayerNames(){
        OutputView.printPlayerNameRequest();
        return InputView.readPlayerNames();
    }

    private void readHitDecision(List<String> playerNames) {
        playerNames.forEach(this::processHit);
    }

    private void processHit(String playerName) {
        while (blackJackService.shouldPlayerHit(playerName)) {
            OutputView.printAskDrawCard(playerName);

            if (!InputView.readHitDecision()) {
                return;
            }

            blackJackService.hitPlayer(playerName);
            OutputView.printPlayerCards(playerName, blackJackService.getPlayerCardNamesByName(playerName));
        }

        OutputView.printBustMessage();
    }
}
