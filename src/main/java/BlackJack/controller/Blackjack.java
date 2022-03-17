package blackJack.controller;

import blackJack.domain.Game;
import blackJack.domain.Result;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;
import blackJack.domain.User.User;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    public void run() {
        List<String> playerNames = InputView.inputPlayerNames();
        Game game = new Game(playerNames, new Dealer());
        OutputView.printDrawMessage(playerNames);
        OutputView.printTotalUserCards(game.getDealer(), game.getPlayers());

        if (game.checkDealerIsBlackJack()){
            printBlackJackResult(game);
            return;
        }

        playGame(game.getDealer(), game.getPlayers());
        OutputView.printTotalResult(makeUserList(game));

        game.makeFinalResult();

        OutputView.printFinalResult(
                game.getDealer().getName(),
                game.getDealerScore(),
                game.getPlayerScore()
        );
    }

    private List<User> makeUserList(Game game) {
        List<User> users = new ArrayList<>();
        users.add(game.getDealer());
        for (Player player : game.getPlayers().getPlayers()) {
            users.add(player);
        }
        return users;
    }

    private void printBlackJackResult(Game game) {
        OutputView.printFinalResult(
                game.getDealer().getName(),
                game.getDealerScore(),
                game.getPlayerScore());
    }

    private void playGame(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            addCardPerPlayer(player);
        }
        while (dealer.isPossibleToAdd()) {
            OutputView.printAddDealerCard();
            dealer.requestCard();
        }
    }

    private void addCardPerPlayer(Player player) {
        while (InputView.askOneMoreCard(player)) {
            player.requestCard();
            OutputView.printPlayerCard(player);
        }
    }
}
