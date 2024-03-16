package blackjack;

import blackjack.domain.betting.Bets;
import blackjack.domain.betting.Money;
import blackjack.domain.betting.OwnedMoney;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
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
        Bets bets = betting(game);
        drawStartCards(game);
        drawPlayersAdditionalCard(game);
        drawDealersAdditionalCard(game);
        printResult(game);
        printPrize(bets, game);
    }

    private BlackJackGame createBlackjackGame() {
        List<String> playerNames = inputView.inputPlayerNames();
        BlackJackGame game = new BlackJackGame(playerNames);
        return game;
    }

    private Bets betting(BlackJackGame game) {
        List<Player> players = game.getPlayers().getPlayers();
        List<OwnedMoney> bets = players.stream()
                .map(this::betEachPlayer)
                .toList();
        return new Bets(bets);
    }

    private OwnedMoney betEachPlayer(Player player) {
        int bettingAmount = inputView.inputBettingAmount(player.getName());
        return new OwnedMoney(player, bettingAmount);
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
        if (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
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

    private void printPrize(Bets bets, BlackJackGame game) {
        outputView.printPrizeTitle();
        List<OwnedMoney> prizes = game.getAllGameResults().stream()
                .map(bets::getPrize)
                .toList();
        Money dealerPrize = calculateDealerPrize(prizes);
        outputView.printDealerPrize(dealerPrize);
        for (OwnedMoney prize : prizes) {
            outputView.printPlayerPrize(prize.getOwner().getName(), prize.getMoney());
        }
    }

    private Money calculateDealerPrize(List<OwnedMoney> prizes) {
        Money dealerPrize = Money.ZERO;
        for (OwnedMoney prize : prizes) {
            dealerPrize = dealerPrize.subtract(prize.getMoney());
        }
        return dealerPrize;
    }
}
