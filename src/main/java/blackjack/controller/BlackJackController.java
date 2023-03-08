package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.GameResult;
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

        List<Player> canHitPlayers = blackJackGame.findCanHitPlayers();
        hitOrStayForCanHitPlayers(canHitPlayers);

        int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(hitCount);
    }

    private void openInitialCards() {
        List<String> playersName = blackJackGame.getPlayersName();
        OutputView.showHandInitialCardsCompleteMessage(playersName);

        Card dealerFirstCard = blackJackGame.openDealerFirstCard();
        OutputView.showDealerFirstCard(dealerFirstCard);

        List<Player> players = blackJackGame.getPlayers();
        players.forEach(this::openPlayerCards);
    }

    private void openPlayerCards(Player player) {
        OutputView.showPlayerCard(player.getName(), player.getCards());
    }

    private void hitOrStayForCanHitPlayers(List<Player> players) {
        players.forEach(this::hitOrStay);
    }

    private void hitOrStay(Player player) {
        Command command = InputView.askToTake(player.getName());
        if (command.isStay()) {
            return;
        }
        blackJackGame.handOneCard(player);
        OutputView.showPlayerCard(player.getName(), player.getCards());
        if (player.canHit()) {
            hitOrStay(player);
        }
    }

    private void showResult() {
        OutputView.showDealerGameResult(blackJackGame.computeDealerGameResult());

        List<String> playersName = blackJackGame.getPlayersName();
        for (String playerName : playersName) {
            GameResult playerResult = blackJackGame.computePlayerGameResult(playerName);
            OutputView.showPlayerGameResult(playerName, playerResult);
        }

        OutputView.showFinalResult(blackJackGame.computePlayerWinResults());
    }
}
