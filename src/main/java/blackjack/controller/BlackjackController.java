package blackjack.controller;

import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = getPlayers();
        Deck deck = Deck.createShuffledDeck(Card.createDeck(), new RandomCardShuffler());
        Dealer dealer = new Dealer(deck);
        readPlayersBetAmount(dealer, players);
        dealInitialHand(dealer, players);
        askHitForAllPlayer(dealer, players);
        dealerHitOrNot(dealer);
        displayResult(dealer, players);
    }

    private Players getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return Players.from(playerNames);
    }

    private void readPlayersBetAmount(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            int stake = inputView.readPlayerStake(player.getName());
            dealer.addBetting(player, stake);
        }
    }

    private void dealInitialHand(Dealer dealer, Players players) {
        players.dealInitialHand(dealer);
        dealer.receiveHand(dealer.drawCard());
        dealer.receiveHand(dealer.drawCard());
        outputView.printInitialCards(dealer.getVisibleCard(), players.getPlayers());
    }

    private void askHitForAllPlayer(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            askHitForPlayer(dealer, player);
        }
    }

    private void askHitForPlayer(Dealer dealer, Player player) {
        while (player.canHit() && inputView.readHitOrNot(player.getName())) {
            player.receiveHand(dealer.drawCard());
            outputView.printPlayerHand(player);
        }
    }

    private void dealerHitOrNot(Dealer dealer) {
        boolean isDealerHit = dealer.hitDealer();
        outputView.printDealerHit(isDealerHit);
    }

    private void displayResult(Dealer dealer, Players players) {
        outputView.printDealerHandAndTotal(dealer.getHand(), dealer.calculateHandTotal());
        outputView.printPlayerHandAndTotal(players.getPlayers());
        Map<Player, Profit> playersProfit = dealer.calculatePlayersProfit();
        Profit dealerProfit = dealer.calculateDealerProfit(playersProfit);
        outputView.printProfit(dealerProfit, playersProfit);
    }
}
