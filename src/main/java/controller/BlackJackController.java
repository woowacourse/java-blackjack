package controller;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();
        initialTurn(players, dealer);
        hitCardByPlayers(players);
        hitCardByDealer(dealer);
    }

    private Players createPlayers() {
        String playerNames = inputView.inputPlayerName();
        return Players.of(playerNames);
    }

    private void initialTurn(Players players, Dealer dealer) {
        players.initialTurn();
        dealer.hitInitialTurn();
        outputView.showInitialTurnStatus(players, dealer);
    }


    private void hitCardByPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            hitCardByPlayer(player);
        }
    }

    private void hitCardByPlayer(Player player) {
        while (player.canDrawCard() && inputView.inputMoreCardOrNot(player.getName())) {
            player.hit(Card.draw());
            outputView.showPlayerCardStatus(player);
        }
    }

    private void hitCardByDealer(Dealer dealer) {
        if (dealer.canDrawCard()) {
            dealer.hit(Card.draw());
            outputView.showDealerHitCardMessage();
        }
    }
}
