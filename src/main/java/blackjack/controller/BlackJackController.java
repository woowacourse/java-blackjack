package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Player;
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
        List<String> playersName = InputView.askPlayersName();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), playersName);
    }

    private void startGame() {
        blackJackGame.handInitialCards();
        openInitialCards();

        blackJackGame.getPlayers().forEach(this::hitOrStay);

        int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(hitCount);
    }

    private void openInitialCards() {
        List<String> playersName = blackJackGame.getPlayersName();
        OutputView.showHandInitialCardsCompleteMessage(playersName);

        Card dealerFirstCard = blackJackGame.openDealerFirstCard();
        OutputView.showDealerFirstCard(dealerFirstCard);

        List<Player> players = blackJackGame.getPlayers();
        players.forEach(OutputView::showPlayerCard);
    }

    private void hitOrStay(Player player) {
        if (!player.canHit()) {
            return;
        }

        Command command = InputView.askToTake(player.getName());
        if (command.isStay()) {
            return;
        }

        blackJackGame.handOneCard(player);
        OutputView.showPlayerCard(player);
        hitOrStay(player);
    }

    private void showResult() {
        OutputView.showDealerGameResult(blackJackGame.computeDealerGameResult());

        List<Player> players = blackJackGame.getPlayers();
        players.forEach(OutputView::showPlayerGameResult);

        OutputView.showFinalResult(blackJackGame.computePlayerWinResults());
    }
}
