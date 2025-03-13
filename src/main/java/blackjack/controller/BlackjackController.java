package blackjack.controller;

import blackjack.model.BettingTable;
import blackjack.model.participant.Dealer;
import blackjack.model.card.Deck;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.GamePlayers;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = createDealer();
        GamePlayers gamePlayers = getGamePlayers();
        BettingTable bettingTable = getBettingTable(gamePlayers);
        playBlackjack(dealer, gamePlayers);
        displayResult(dealer, gamePlayers, bettingTable);
    }

    private BettingTable getBettingTable(GamePlayers gamePlayers) {
        BettingTable bettingTable = new BettingTable();
        for (Player player : gamePlayers) {
            int betAmount = inputView.readBetAmount(player.getName());
            bettingTable.bet(player, betAmount);
        }
        return bettingTable;
    }

    private Dealer createDealer() {
        return new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
    }

    private GamePlayers getGamePlayers() {
        outputView.printStartBanner();
        List<Player> players = createPlayersFromInput();
        return GamePlayers.createForNewGame(players);
    }

    private List<Player> createPlayersFromInput() {
        return inputView.readPlayerNames()
                .stream()
                .map(name -> new Player(new Name(name)))
                .toList();
    }

    private void playBlackjack(Dealer dealer, GamePlayers gamePlayers) {
        initializeHand(dealer, gamePlayers);
        displayInitialHand(dealer, gamePlayers);
        askHitForAllPlayer(dealer, gamePlayers);
        askHitForDealer(dealer);
    }

    private void initializeHand(Dealer dealer, GamePlayers gamePlayers) {
        for (Player player : gamePlayers) {
            dealer.dealCard(player);
            dealer.dealCard(player);
        }
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);
    }

    private void displayInitialHand(Dealer dealer, GamePlayers gamePlayers) {
        outputView.printInitialCards(dealer.getVisibleCard(), gamePlayers.getPlayers());
    }

    private void askHitForAllPlayer(Dealer dealer, GamePlayers gamePlayers) {
        for (Player player : gamePlayers) {
            askHit(dealer, player);
        }
    }

    private void askHit(Dealer dealer, Player player) {
        while (player.canHit() && wantsToHit(player)) {
            dealer.dealCard(player);
            outputView.printPlayerHand(player);
        }
    }

    private boolean wantsToHit(Player player) {
        return inputView.readHitOrNot(player.getName()).isHit();
    }

    private void askHitForDealer(Dealer dealer) {
        boolean isDealerHitting = dealer.canHit() && dealer.decideHit();
        if (isDealerHitting) {
            dealer.dealCard(dealer);
        }
        outputView.printDealerHit(isDealerHitting);
    }

    private void displayResult(Dealer dealer, GamePlayers gamePlayers, BettingTable bettingTable) {
        outputView.printDealerHandAndTotal(dealer.getHand(), dealer.getTotal());
        outputView.printPlayerHandAndTotal(gamePlayers.getPlayers());
        displayFinalProfit(dealer, bettingTable);
    }

    private void displayFinalProfit(Dealer dealer, BettingTable bettingTable) {
        Map<Player, Integer> payouts = bettingTable.calculatePayouts(dealer);
        Map<Player, Integer> betting = bettingTable.getBetting();
        int dealerProfit = calculateDealerProfit(betting, payouts);
        outputView.printFinalProfitHeader();
        outputView.printDealerFinalProfit(dealerProfit);
        betting.forEach((player, betAmount) ->
                outputView.printPlayerFinalProfit(player.getName(), betAmount, payouts.get(player))
        );
    }

    private int calculateDealerProfit(Map<Player, Integer> betting, Map<Player, Integer> payouts) {
        return betting.entrySet()
                .stream()
                .mapToInt(bet -> bet.getValue() - payouts.get(bet.getKey()))
                .sum();
    }
}
