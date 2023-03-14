package controller;

import domain.model.Dealer;
import domain.model.Player;
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
        makePlayers();
        giveInitialCards();
        giveAdditionalCard();
        printTotalCardState();
        printProfits();
    }

    private void makePlayers() {
        final List<String> names = ioView.inputNames();
        final List<Double> bets = ioView.inputBattings(names);
        blackJackGame.makePlayers(names, bets);
    }

    private void giveInitialCards() {
        blackJackGame.giveInitCards();
        ioView.printInitialCards(blackJackGame.getDealer(), blackJackGame.getPlayers());
    }

    private void giveAdditionalCard() {
        final List<Player> players = blackJackGame.getPlayers();
        IntStream.range(0, players.size()).
            forEach(index -> {
                final Player player = givePlayerAdditionalCard(players.get(index));
                blackJackGame.setPlayers(index, player);
            });
        giveDealerAdditionalCard(blackJackGame.getDealer());
    }

    private Player givePlayerAdditionalCard(final Player player) {
        while (getIntentReceiveCard(player) && blackJackGame.giveCard(player)) {
            ioView.printCard(player);
        }
        return player;
    }

    private boolean getIntentReceiveCard(final Player player) {
        return ioView.inputCardIntent(player.getName());
    }

    private void giveDealerAdditionalCard(final Dealer dealer) {
        while (blackJackGame.giveCard(dealer)) {
            ioView.printDealerReceiveNotice();
        }
    }

    private void printTotalCardState() {
        ioView.printTotalCardState(blackJackGame.getDealer(), blackJackGame.getPlayers());
    }

    private void printProfits() {
        final List<Profit> playerProfits = blackJackGame.calculatePlayersProfit(blackJackGame.getPlayers(),
            blackJackGame.getDealer());
        final Profit dealerProfit = blackJackGame.calculateDealerProfit(playerProfits);
        ioView.printProfits(dealerProfit, playerProfits, blackJackGame.getPlayerNames());
    }
}
