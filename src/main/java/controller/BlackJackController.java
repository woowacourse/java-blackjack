package controller;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Players;
import domain.service.BlackJackGame;
import domain.vo.Profit;
import java.util.List;
import java.util.stream.IntStream;
import view.IOView;

public class BlackJackController {

    private final BlackJackGame blackJackGame;
    private final IOView ioView;

    public BlackJackController(final BlackJackGame blackJackGame, final IOView ioView) {
        this.blackJackGame = blackJackGame;
        this.ioView = ioView;
    }

    public void play() {
        final Players players = getPlayers();
        final Dealer dealer = Dealer.withEmptyCards();
        giveInitialCards(dealer, players);
        getPlayerAdditionalCard(players);
        getDealerAdditionalCard(dealer);
        printTotalCardState(dealer, players);
        printProfits(dealer, players);
    }

    private Players getPlayers() {
        final List<String> names = ioView.inputNames();
        final List<Double> bets = ioView.inputBattings(names);
        return blackJackGame.makePlayers(names, bets);
    }

    private void giveInitialCards(final Dealer dealer, final Players players) {
        blackJackGame.giveInitCards(dealer, players);
        ioView.printInitialCards(dealer, players);
    }

    private void getPlayerAdditionalCard(final Players players) {
        IntStream.range(0, players.count()).forEach(index -> {
            final Player player = getPlayerAdditionalCard(players.get(index));
            blackJackGame.setPlayers(players, player, index);
        });
    }

    private Player getPlayerAdditionalCard(final Player player) {
        while (getIntentReceiveCard(player) && blackJackGame.giveCard(player)) {
            ioView.printCard(player);
        }
        return player;
    }

    private boolean getIntentReceiveCard(final Player player) {
        return ioView.inputCardIntent(player.getName());
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (blackJackGame.giveCard(dealer)) {
            ioView.printDealerReceiveNotice();
        }
    }

    private void printTotalCardState(final Dealer dealer, final Players players) {
        ioView.printTotalCardState(dealer, players);
    }

    private void printProfits(final Dealer dealer, final Players players) {
        final List<Profit> playerProfits = blackJackGame.calculatePlayersProfit(players, dealer);
        final Profit dealerProfit = blackJackGame.calculateDealerProfit(playerProfits);
        ioView.printProfits(dealerProfit, playerProfits, players.getNames());
    }
}
