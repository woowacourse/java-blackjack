package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Money;
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
        blackJackGame.getPlayers().forEach(this::savePlayerBetMoney);
        blackJackGame.handInitialCards();
        openInitialCards();

        blackJackGame.getPlayers().forEach(this::hitOrStay);
        hitOrStayForDealer(blackJackGame.getDealer());
    }

    private void savePlayerBetMoney(Player player) {
        Money betMoney = InputView.askBetMoney(player.getName());
        Dealer dealer = blackJackGame.getDealer();
        dealer.addPlayerBetMoney(player, betMoney);
    }

    private void openInitialCards() {
        List<String> playersName = blackJackGame.getPlayersName();
        OutputView.showHandInitialCardsCompleteMessage(playersName);

        Card dealerFirstCard = blackJackGame.getDealerFirstCard();
        OutputView.showDealerFirstCard(dealerFirstCard);

        blackJackGame.getPlayers().forEach(OutputView::showPlayerCard);
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

    private void hitOrStayForDealer(Dealer dealer) {
        if (!dealer.canHit()) {
            return;
        }

        blackJackGame.handOneCard(dealer);
        OutputView.showDealerHitMessage();
        hitOrStayForDealer(dealer);
    }

    private void showResult() {
        OutputView.showDealerGameResult(blackJackGame.getDealer());

        List<Player> players = blackJackGame.getPlayers();
        players.forEach(OutputView::showPlayerGameResult);

        OutputView.showGameResult(blackJackGame.getGameResult());
    }
}
