package blackJack.controller;

import blackJack.domain.Game;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;
import blackJack.domain.User.User;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Blackjack {

    public void run() {
        List<String> playerNames = InputView.inputPlayerNames();
        Map<String, Integer> bettingMoneys = getBettingMoneys(playerNames);
        Game game = new Game(playerNames, new Dealer(), bettingMoneys);
        OutputView.printDrawMessage(playerNames);
        OutputView.printTotalUserCards(game.getDealer(), game.getPlayers());

        if (game.checkDealerIsBlackJack()) {
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

    private Map<String, Integer> getBettingMoneys(List<String> playerNames) {
        Map<String, Integer> bettingMoneys = new HashMap<>();
        for (String playerName : playerNames) {
            bettingMoneys.put(playerName, InputView.inputBettingMoney(playerName));
        }
        return bettingMoneys;
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
            dealer.requestCard(CARD_CACHE.poll());
        }
    }

    private void addCardPerPlayer(Player player) {
        while (player.isPossibleToAdd() && InputView.askOneMoreCard(player)) {
            player.requestCard(CARD_CACHE.poll());
            OutputView.printPlayerCard(player);
        }
    }
}
