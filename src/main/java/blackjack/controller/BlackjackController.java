package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Answer;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = participatePlayers();
        List<User> users = setUpUsers(dealer, players);
        handOutTwoCardsToUsers(users);
        showUsersCards(dealer, players);
        proceedPlayersRound(players);
        proceedDealerRound(dealer);
        OutputView.printCardsWithTotalValue(users);
        OutputView.printResults(dealer, ResultBoard.of(players, dealer));
    }

    private Players participatePlayers() {
        OutputView.printInputNames();
        List<String> playersNames = InputView.inputNames();
        return Players.of(playersNames);
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
                Answer.of(InputView.inputDrawAnswer()).isYes()) {
            player.hit();
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isAbleToAskHit(Player player) {
        if (player.isAbleToHit()) {
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
