package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, BlackJackInitManager blackJackInitManager) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck());
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = blackJackInitManager.savePlayers(names, Hand::new);
        Dealer dealer = blackJackInitManager.saveDealer(Hand::new);

        giveStartingCards(players, dealer);

        giveMoreCardForPlayers(players);
        giveMoreCardForDealer(dealer);

        printCardResult(players, dealer);
        printGameResult();
    }

    private void giveMoreCardForDealer(Dealer dealer) {
        while (gameRuleEvaluator.canTakeCardFor(dealer)) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(dealer.getCardHolder());
        }
    }

    private void giveMoreCardForPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            giveMoreCardForPlayer(player);
        }
    }

    private void giveMoreCardForPlayer(Player player) {
        // TODO DEPTH 낮추는 방법 찾기
        while (gameRuleEvaluator.canTakeCardFor(player)) {
            Confirmation confirmation = InputView.askToGetMoreCard(player);

            if (confirmation.equals(Confirmation.N)) {
                break;
            }
            blackjackProcessManager.giveCard(player.getCardHolder());

            if (gameRuleEvaluator.isBusted(player)) {
                OutputView.printBustedPlayer(player);
            }
        }
    }

    private void giveStartingCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCards(player.getCardHolder());
        }
        blackjackProcessManager.giveStartingCards(dealer.getCardHolder());

        OutputView.printStartingCardsStatuses(dealer, players);
    }

    private void printCardResult(Players players, Dealer dealer) {
        blackjackProcessManager.calculateCardResult(players, dealer, gameRuleEvaluator);
        OutputView.printCardResult(players, dealer);
    }

    private void printGameResult() {
        OutputView.printGameResult(blackjackProcessManager.getDealerResult(),
                blackjackProcessManager.getPlayersResult());
    }
}
