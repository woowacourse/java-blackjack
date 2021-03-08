package blackjack.controller;

import blackjack.domain.card.Deck;
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
    private static final String YES = "y";

    public void run() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = participatePlayers();
        List<User> users = setUpUsers(dealer, players);
        distributeToUsers(dealer, players, deck);
        showUsersCards(dealer, players);
        askToPlayers(players, deck);
        isDealerHit(dealer, deck);
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

    private void distributeToUsers(Dealer dealer, Players players, Deck deck) {
        dealer.distribute(deck.popTwo());
        players.distributeToEachPlayer(deck);
    }

    private void showUsersCards(Dealer dealer, Players players) {
        OutputView.printDistribute(dealer, players);
        OutputView.printDealerCard(dealer);
        OutputView.printPlayersCards(players);
    }

    private void askToPlayers(Players players, Deck deck) {
        for (Player player : players.players()) {
            askForHit(player, deck);
        }
    }

    private void askForHit(Player player, Deck deck) {
        while (isPlayerHit(player) && isYes(InputView.inputHit())) {
            player.draw(deck);
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

    private boolean isYes(String answer) {
        return answer.equals(YES);
    }

    private void isDealerHit(Dealer dealer, Deck deck) {
        if (dealer.isHit()) {
            dealer.draw(deck);
            OutputView.printDealerHit(dealer);
            return;
        }
        OutputView.printDealerNotHit(dealer);
    }
}
