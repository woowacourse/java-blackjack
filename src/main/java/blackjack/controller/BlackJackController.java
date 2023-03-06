package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        generateGame();
        startGame();
        showResult();
    }

    private void generateGame() {
        List<String> playerNames = InputView.askPlayerNames();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), playerNames);
    }

    private void startGame() {
        blackJackGame.handInitialCards();
        openInitialCards();

        hitOrStayForNotBustPlayers(blackJackGame.findNotBustPlayerNames());
        int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(hitCount);
    }

    private void openInitialCards() {
        List<String> playerNames = blackJackGame.getPlayerNames();
        OutputView.showHandInitialCardsCompleteMessage(playerNames);

        Card dealerFirstCard = blackJackGame.openDealerFirstCard();
        OutputView.showDealerFirstCard(dealerFirstCard);

        playerNames.forEach(this::openPlayerCards);
    }

    private void openPlayerCards(String playerName) {
        List<Card> playerCards = blackJackGame.openPlayerCards(playerName);
        OutputView.showPlayerCard(playerName, playerCards);
    }

    private void hitOrStayForNotBustPlayers(List<String> playerNames) {
        playerNames.forEach(this::hitOrStay);
    }

    private void hitOrStay(String playerName) {
        Command command = InputView.askToTake(playerName);
        if (command.isStay()) {
            return;
        }
        boolean keepGoing = blackJackGame.handOneCard(playerName);
        OutputView.showPlayerCard(playerName, blackJackGame.openPlayerCards(playerName));
        if (keepGoing) {
            hitOrStay(playerName);
        }
    }

    private void showResult() {
        OutputView.showParticipantGameResults(blackJackGame.computeDealerGameResult(),
                blackJackGame.computePlayerGameResults());

        OutputView.showFinalResult(blackJackGame.computePlayerWinResults());
    }
}
