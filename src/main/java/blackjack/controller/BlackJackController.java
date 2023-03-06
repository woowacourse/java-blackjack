package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private static final String DealerName = "딜러";
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
        playerNames.forEach(this::hitOrStay);
    }

    private void hitOrStay(String playerName) {
        String toTakeKeyword = InputView.askToTake(playerName);
        // TODO 키워드 Enum으로 분리, HIT or STAY 여부 도메인에서 관리
        if (toTakeKeyword.equals("n")) {
            return;
        }
        boolean keepGoing = blackJackGame.handOneCard(playerName);
        OutputView.showPlayerCard(playerName, blackJackGame.openCardsByName(playerName));
        if (keepGoing) {
            hitOrStay(playerName);
        }
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
