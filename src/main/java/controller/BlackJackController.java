package controller;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Players;
import domain.model.Profit;
import domain.service.BlackJackGame;
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
        List<String> names = ioView.inputNames();
        List<Double> battings = ioView.inputBattings(names);
        return Players.from(names, battings);
    }

    private void giveInitialCards(final Dealer dealer, final Players players) {
        blackJackGame.giveInitCards(dealer, players);
        ioView.printInitialCards(dealer, players);
    }

    private void getPlayerAdditionalCard(final Players players) {
        IntStream.range(0, players.count()).forEach(index -> {
            Player player = getPlayerAdditionalCard(players.get(index));
            players.set(index, player);
        });
    }

    private Player getPlayerAdditionalCard(final Player player) {
        while (player.canReceiveCard() && getIntentReceiveCard(player)) {
            blackJackGame.giveCard(player);
            ioView.printCard(player);
        }
        return player;
    }

    private boolean getIntentReceiveCard(final Player player) {
        return ioView.inputCardIntent(player.getName());
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            ioView.printDealerReceiveNotice();
            blackJackGame.giveCard(dealer);
        }
    }

    private void printTotalCardState(final Dealer dealer, final Players players) {
        ioView.printTotalCardState(dealer, players);
    }

    private void printProfits(final Dealer dealer, final Players players) {
        final List<Profit> profits = blackJackGame.calculatePlayersProfit(players, dealer);
        final Profit dealerProfit = Profit.makeDealerProfitFrom(profits);
        ioView.printProfits(dealerProfit, profits, players.getNames());
    }
}
