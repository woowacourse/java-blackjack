package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final static String DealerName = "딜러";
    private BlackJackGame blackJackGame;

    public void run() {
        initialize();
        startGame();
        hitOrStayForAvailablePlayers(blackJackGame.findAvailablePlayerNames());
        hitUntilDealerAvailable();
        totalUp();
    }

    private void initialize() {
        List<String> playerNames = InputView.askPlayerNames();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), DealerName, playerNames);
    }

    private void startGame() {
        blackJackGame.handOut();
        Map<String, List<Card>> allOpenedCardsByParticipantName = blackJackGame.openHandOutCards();
        OutputView.showOpenCards(DealerName, allOpenedCardsByParticipantName);
    }

    private void hitOrStayForAvailablePlayers(List<String> playerNames) {
        playerNames.forEach(this::repeatHitOrStayUntilPlayerWants);
    }

    private void repeatHitOrStayUntilPlayerWants(String playerName) {
        HitCommand hitCommand = refreshHitCommand(playerName);
        while (hitCommand == HitCommand.YES) {
            blackJackGame.handOneCard(playerName);
            OutputView.showPlayerCard(playerName, blackJackGame.openCardsByName(playerName));
            hitCommand = refreshHitCommand(playerName);
        }
    }

    private HitCommand refreshHitCommand(String playerName) {
        if (blackJackGame.isAvailablePlayer(playerName)) {
            return InputView.askToHit(playerName);
        }
        return HitCommand.NO;
    }

    private void hitUntilDealerAvailable() {
        int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(DealerName, hitCount);
    }

    private void totalUp() {
        OutputView.showAllFinalCards(blackJackGame.openAllFinalCards());
        OutputView.showAllJudgeResults(blackJackGame.computeJudgeResultsByPlayer());
    }
}
