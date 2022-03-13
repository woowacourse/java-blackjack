package controller;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.Result;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();
        Deck deck = Deck.of();
        initialTurn(players, dealer, deck);
        hitCardByPlayers(players, deck);
        hitCardByDealer(dealer,deck);
        showFinalTurn(players, dealer);
        showResult(players, dealer);
    }

    private Players createPlayers() {
        String playerNames = inputView.inputPlayerName();
        try {
            return Players.of(playerNames);
        } catch (IllegalArgumentException exception) {
            outputView.printError(exception.getMessage());
            return createPlayers();
        }
    }

    private void initialTurn(Players players, Dealer dealer, Deck deck) {
        players.runInitialTurn(deck);
        dealer.hitInitialTurn(deck);
        outputView.showInitialTurnStatus(players, dealer);
    }


    private void hitCardByPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitCardByPlayer(player, deck);
        }
    }

    private void hitCardByPlayer(Player player, Deck deck) {
        while (player.canDrawCard() && inputView.inputMoreCardOrNot(player.getName())) {
            player.hit(deck.handOut());
            outputView.showPlayerCardStatus(player);
        }
    }

    private void hitCardByDealer(Dealer dealer, Deck deck) {
        if (dealer.canDrawCard()) {
            dealer.hit(deck.handOut());
            outputView.showDealerHitCardMessage();
        }
    }

    private void showFinalTurn(Players players, Dealer dealer) {
        outputView.showFinalTurnStatus(players, dealer);
    }

    private void showResult(Players players, Dealer dealer) {
        List<Result> playersResult = players.checkResults(dealer);
        
        outputView.showResult(dealer.checkResult(playersResult), players.toNames(),
            players.checkResults(dealer));
    }
}
