package blackJack.controller;

import blackJack.domain.Game;
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
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        Game game = new Game(inputPlayerNames, new Dealer());
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(game.getDealer(), game.getPlayers());

        checkDealerIsBlackJack(game);

        playGame(game.getDealer(), game.getPlayers());
        OutputView.printTotalResult(calculateDealerAndPlayersScore(game));

        makeResults(game);

        OutputView.printFinalResult(
                game.getDealer().getName(),
                game.getDealerScore(),
                game.getPlayerScore()
        );
    }

    private List<User> calculateDealerAndPlayersScore(Game game) {
        List<User> users = new ArrayList<>();
        users.add(game.getDealer());
        for (Player player : game.getPlayers().getPlayers()) {
            users.add(player);
        }
        return users;
    }

    private void makeResults(Game game) {
        game.makePlayerResult();
        game.makeDealerResult(game.getPlayerScore());
    }

    private void checkDealerIsBlackJack(Game game) {
        if (game.checkDealerIsBlackJack()) {
            OutputView.printFinalResult(
                    game.getDealer().getName(),
                    game.getDealerScore(),
                    game.getPlayerScore()
            );
        }
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
