package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = participatePlayers();
        List<User> users = setUpUsers(dealer, players);
        distributeToUsers(dealer, players);
        showUsersCards(dealer, players);
        askToPlayers(players);
        isDealerHit(dealer);
        OutputView.printResults(users);
        OutputView.printResultBoard(dealer, new ResultBoard(dealer, players));
    }

    private Players participatePlayers() {
        OutputView.printInputNames();
        List<String> names = InputView.inputNames();
        return new Players(names);
    }

    private List<User> setUpUsers(Dealer dealer, Players players) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.players());
        return users;
    }

    private void distributeToUsers(Dealer dealer, Players players) {
        dealer.distribute(Deck.popTwo());
        players.distributeToEachPlayer();
    }

    private void showUsersCards(Dealer dealer, Players players) {
        OutputView.printDistribute(dealer, players);
        OutputView.printDealerCard(dealer);
        OutputView.printPlayersCards(players);
    }

    private void askToPlayers(Players players) {
        for (Player player : players.players()) {
            askForHit(player);
        }
    }

    private void askForHit(Player player) {
        while (isPlayerHit(player) && Hit.isYes(Hit.of(InputView.inputHit()))) {
            player.draw();
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isPlayerHit(Player player) {
        if (player.isHit()) {
            OutputView.printPlayerHit(player);
            return true;
        }
        return false;
    }

    private void isDealerHit(Dealer dealer) {
        if (dealer.isHit()) {
            dealer.draw();
            OutputView.printDealerHit(dealer);
            return;
        }
        OutputView.printDealerNotHit(dealer);
    }
}
