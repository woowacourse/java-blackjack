package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackGame;
import blackjack.domain.bet.Money;
import blackjack.domain.card.BlackJackDeckGenerator;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        generateGame();
        startGame();
        playGame();
        showResult();
    }

    private void generateGame() {
        List<String> playersName = InputView.askPlayersName();
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), playersName);
    }

    private void startGame() {
        blackJackGame.getPlayers().forEach(this::betMoney);
        blackJackGame.handInitialCards();
        openInitialCards();
    }

    private void betMoney(Player player) {
        Money betMoney = InputView.askBetMoney(player.getName());
        blackJackGame.addBetMoneyToPlayer(player, betMoney);
    }

    private void openInitialCards() {
        List<String> playersName = blackJackGame.getPlayersName();
        OutputView.showHandInitialCardsCompleteMessage(playersName);

        Card dealerFirstCard = blackJackGame.getDealerFirstCard();
        OutputView.showDealerFirstCard(dealerFirstCard);

        blackJackGame.getPlayers().forEach(OutputView::showPlayerCard);
    }

    private void playGame() {
        blackJackGame.getPlayers().forEach(this::hitOrStay);
        hitOrStayForDealer();
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

    private void hitOrStayForDealer() {
        if (!blackJackGame.canDealerHit()) {
            return;
        }

        blackJackGame.handOneCardToDealer();
        OutputView.showDealerHitMessage();
        hitOrStayForDealer();
    }

    private void showResult() {
        OutputView.showDealerGameResult(blackJackGame.getDealer());

        List<Player> players = blackJackGame.getPlayers();
        players.forEach(OutputView::showPlayerGameResult);

        OutputView.showGameResult(blackJackGame.getGameResult());
    }
}
