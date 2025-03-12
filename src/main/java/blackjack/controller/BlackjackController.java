package blackjack.controller;

import blackjack.model.participant.Dealer;
import blackjack.model.MatchResult;
import blackjack.model.card.Deck;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.GamePlayers;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
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
        Dealer dealer = getDealer();
        GamePlayers gamePlayers = getGamePlayers();
        playBlackjack(dealer, gamePlayers);
        displayResult(dealer, gamePlayers);
    }

    private Dealer getDealer() {
        return new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
    }

    private GamePlayers getGamePlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return GamePlayers.createForNewGame(playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .toList());
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
        if (dealer.canHit() && dealer.decideHit()) {
            dealer.dealCard(dealer);
            outputView.printDealerHit(true);
        }
    }

    private void displayResult(Dealer dealer, GamePlayers gamePlayers) {
        outputView.printDealerHandAndTotal(dealer.getHand(), dealer.getTotal());
        outputView.printPlayerHandAndTotal(gamePlayers.getPlayers());
        outputView.printMatchResult(judgeMatchResults(dealer, gamePlayers));
    }

    private Map<Player, MatchResult> judgeMatchResults(Dealer dealer, GamePlayers gamePlayers) {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player player : gamePlayers) {
            results.put(player, dealer.compareWith(player).getReversed());
        }
        return results;
    }
}
