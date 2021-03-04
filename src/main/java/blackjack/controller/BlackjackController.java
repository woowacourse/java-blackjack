package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = participateUsers();
        List<User> users = setUpUsers(dealer, players);
        handOutTwoCardsToUsers(users);
        showUsersCards(dealer, players);
        proceedPlayersRound(players);
        proceedDealerRound(dealer);
        OutputView.printCardsWithTotalValue(users);
        OutputView.printResults(dealer, new ResultBoard(dealer, players));
    }

    private Players participateUsers() {
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

    private void handOutTwoCardsToUsers(List<User> users) {
        users.forEach(user -> user.receiveCards(Deck.popTwo()));
    }

    private void showUsersCards(Dealer dealer, Players players) {
        OutputView.printDistribute(dealer, players);
        OutputView.printDealerCard(dealer);
        OutputView.printUsersCards(players);
    }

    private void proceedPlayersRound(Players players) {
        for (Player player : players.players()) {
            askWantToHit(player);
        }
    }

    private void askWantToHit(Player player) {
        while (isAbleToAskHit(player) &&
                Answer.isYes(Answer.of(InputView.inputDrawAnswer()))) {
            player.hit();
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isAbleToAskHit(Player player) {
        if (player.isNotBust()) {
            OutputView.printWantHit(player);
            return true;
        }
        return false;
    }

    private void proceedDealerRound(Dealer dealer) {
        if (dealer.isAbleHit()) {
            dealer.hit();
            OutputView.printDealerDrawable(dealer);
            return;
        }
        OutputView.printDealerNotDrawable(dealer);
    }
}
