package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.money.Money;
import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        BlackJackGame game = createBlackjackGame();
        drawStartCards(game);
        drawPlayersAdditionalCard(game);
        drawDealersAdditionalCard(game);
        printResult(game);
        printPrize(game);
    }

    private BlackJackGame createBlackjackGame() {
        List<String> playerNames = inputView.inputPlayerNames();
        List<Integer> bets = betAllPlayer(playerNames);
        return new BlackJackGame(playerNames, bets);
    }

    private List<Integer> betAllPlayer(List<String> names) {
        return names.stream()
                .map(this::betEachPlayer)
                .toList();
    }

    private int betEachPlayer(String name) {
        return inputView.inputBettingAmount(name);
    }

    private void drawStartCards(BlackJackGame game) {
        game.drawStartCards();
        outputView.printStartStatus(game.getDealer(), game.getPlayers());
    }

    private void drawPlayersAdditionalCard(BlackJackGame game) {
        Players players = game.getPlayers();
        for (Player player : players.getPlayers()) {
            drawEachPlayerAdditionalCard(player, game.getDeck());
        }
    }

    private void drawEachPlayerAdditionalCard(Player player, Deck deck) {
        while (player.isDrawable() && inputView.isPlayerWantToDraw(player.getName())) {
            player.drawAdditionalCard(deck);
            outputView.printPlayerCards(player);
        }
    }

    private void drawDealersAdditionalCard(BlackJackGame game) {
        Dealer dealer = game.getDealer();
        while (dealer.isDrawable()) {
            dealer.drawAdditionalCard(game.getDeck());
            outputView.printDealerDraw();
        }
    }

    private void printResult(BlackJackGame game) {
        Dealer dealer = game.getDealer();
        Players players = game.getPlayers();
        outputView.printEndingStatus(dealer, players);
    }

    private void printPrize(BlackJackGame game) {
        outputView.printPrizeTitle();
        printDealerPrize(game);
        printPlayersPrize(game.getDealer(), game.getPlayers());
    }

    private void printDealerPrize(BlackJackGame game) {
        Money dealerPrize = game.calculateDealerPrize();
        outputView.printDealerPrize(dealerPrize);
    }

    private void printPlayersPrize(Dealer dealer, Players players) {
        for (BettingPlayer bettingPlayer : players.getBettingPlayers()) {
            Money prize = bettingPlayer.calculatePrize(dealer);
            outputView.printPlayerPrize(bettingPlayer.getName(), prize);
        }
    }
}
