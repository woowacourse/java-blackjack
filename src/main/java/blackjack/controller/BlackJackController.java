package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        List<String> playerNames = InputView.askPlayerNames();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), playerNames);

        startGame();
        hitOrStayForAvailablePlayers(blackJackGame.findAvailablePlayerNames());
        int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(hitCount);
        OutputView.showParticipantGameResults(blackJackGame.computeDealerGameResult(),
                blackJackGame.computePlayerGameResults());

        OutputView.showFinalResult(blackJackGame.computePlayerWinResults());
    }

    private void startGame() {
        blackJackGame.handInitialCards();
        Card dealerFirstCard = blackJackGame.openDealerFirstCard();
        Map<String, List<Card>> playersCards = blackJackGame.openPlayersCards();
        OutputView.showOpenCards(dealerFirstCard, playersCards);
    }

    private void hitOrStayForAvailablePlayers(List<String> playerNames) {
        playerNames.forEach(this::hitOrStay);
    }

    private void hitOrStay(String playerName) {
        String toTakeKeyword = InputView.askToTake(playerName);
        if (toTakeKeyword.equals("n")) {
            return;
        }
        boolean keepGoing = blackJackGame.handOneCard(playerName);
        OutputView.showPlayerCard(playerName, blackJackGame.openPlayerCards(playerName));
        if (keepGoing) {
            hitOrStay(playerName);
        }
    }
}
