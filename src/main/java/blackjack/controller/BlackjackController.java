package blackjack.controller;

import blackjack.model.BettingTable;
import blackjack.model.participant.Dealer;
import blackjack.model.card.Deck;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.GamePlayers;
import blackjack.view.GamePlayView;
import blackjack.view.GameResultView;
import blackjack.view.GameSetupView;
import java.util.List;
import java.util.Map;

public final class BlackjackController {

    private final GameSetupView gameSetupView;
    private final GamePlayView gamePlayView;
    private final GameResultView gameResultView;

    public BlackjackController(GameSetupView gameSetupView, GamePlayView gamePlayView, GameResultView gameResultView) {
        this.gameSetupView = gameSetupView;
        this.gamePlayView = gamePlayView;
        this.gameResultView = gameResultView;
    }

    public void run() {
        Dealer dealer = createDealer();
        GamePlayers gamePlayers = getGamePlayers();
        BettingTable bettingTable = getBettingTable(gamePlayers);
        playBlackjack(dealer, gamePlayers);
        displayResult(dealer, gamePlayers, bettingTable);
    }

    private Dealer createDealer() {
        return new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
    }

    private GamePlayers getGamePlayers() {
        gameSetupView.printStartBanner();
        List<Player> players = createPlayersFromInput();
        return GamePlayers.createForNewGame(players);
    }

    private BettingTable getBettingTable(GamePlayers gamePlayers) {
        BettingTable bettingTable = new BettingTable();
        for (Player player : gamePlayers) {
            int betAmount = gameSetupView.readBetAmount(player.getName());
            bettingTable.bet(player, betAmount);
        }
        return bettingTable;
    }

    private List<Player> createPlayersFromInput() {
        return gameSetupView.readPlayerNames()
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
        gamePlayView.printInitialCards(dealer.getVisibleCard(), gamePlayers.getPlayers());
    }

    private void askHitForAllPlayer(Dealer dealer, GamePlayers gamePlayers) {
        for (Player player : gamePlayers) {
            askHit(dealer, player);
        }
    }

    private void askHit(Dealer dealer, Player player) {
        while (!player.isFinished()) {
            playerHitOrStand(dealer, player);
        }
    }

    private void playerHitOrStand(Dealer dealer, Player player) {
        if (wantsToHit(player)) {
            dealer.dealCard(player);
            gamePlayView.printPlayerHand(player);
            return;
        }
        player.stand();
        gamePlayView.printPlayerHand(player);
    }

    private boolean wantsToHit(Player player) {
        return gamePlayView.readHitOrNot(player.getName())
                .isHit();
    }

    private void askHitForDealer(Dealer dealer) {
        while (!dealer.isFinished()) {
            dealer.dealCard(dealer);
            gamePlayView.printDealerHit(true);
        }
        gamePlayView.printDealerHit(false);
    }

    private void displayResult(Dealer dealer, GamePlayers gamePlayers, BettingTable bettingTable) {
        gameResultView.printDealerHandAndTotal(dealer.getState().getHand().getCards(), dealer.getState().getHand().getTotal());
        gameResultView.printPlayerHandAndTotal(gamePlayers.getPlayers());
        displayFinalProfit(dealer, bettingTable);
    }

    private void displayFinalProfit(Dealer dealer, BettingTable bettingTable) {
        Map<Player, Integer> payouts = bettingTable.calculatePayouts(dealer);
        Map<Player, Integer> betting = bettingTable.getBetting();
        int dealerProfit = calculateDealerProfit(betting, payouts);
        gameResultView.printFinalProfitHeader();
        gameResultView.printDealerFinalProfit(dealerProfit);
        betting.forEach((player, betAmount) ->
                gameResultView.printPlayerFinalProfit(player.getName(), betAmount, payouts.get(player))
        );
    }

    private int calculateDealerProfit(Map<Player, Integer> betting, Map<Player, Integer> payouts) {
        return betting.entrySet()
                .stream()
                .mapToInt(bet -> bet.getValue() - payouts.get(bet.getKey()))
                .sum();
    }
}
