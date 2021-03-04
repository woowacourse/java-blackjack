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
        isDealerDrawable(dealer);
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
            askForDraw(player);
        }
    }

    private void askForDraw(Player player) {
        while (isDrawable(player) && DrawAnswer.isYes(DrawAnswer.of(InputView.inputDrawAnswer()))) {
            player.draw();
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isDrawable(Player player) {
        if (player.isDrawable()) {
            OutputView.printMoreDraw(player);
            return true;
        }
        return false;
    }

    private void isDealerDrawable(Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.draw();
            OutputView.printDealerDrawable(dealer);
            return;
        }
        OutputView.printDealerNotDrawable(dealer);
    }
}
