package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
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
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck(),
                PlayersResults.create());
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = blackJackInitManager.savePlayers(names);
        Dealer dealer = blackJackInitManager.saveDealer();

        giveStartingCards(players, dealer);

        giveMoreCardFor(players);
        giveMoreCardFor(dealer);

        List<PlayerResult> playerResults = getCardResultOfPlayer(players, dealer);
        DealerResult dealerResult = getCardResultOfDealer(dealer);

        OutputView.printCardResult(playerResults, dealerResult, dealer);
        OutputView.printGameResult(dealerResult, playerResults);
    }

    private void giveMoreCardFor(Dealer dealer) {
        while (gameRuleEvaluator.canTakeCardFor(dealer)) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(dealer.getCardHolder());
        }
    }

    private void giveMoreCardFor(Players players) {
        for (Player player : players.getPlayers()) {
            giveMoreCardFor(player);
        }
    }

    private void giveMoreCardFor(Player player) {
        Confirmation confirmation = InputView.askToGetMoreCard(player);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(player);
            return;
        }

        blackjackProcessManager.giveCard(player.getCardHolder());
        OutputView.printCardResult(player);

        if (gameRuleEvaluator.isBustedFor(player)) {
            OutputView.printBustedPlayer(player);
            return;
        }

        if (gameRuleEvaluator.canTakeCardFor(player)) {
            giveMoreCardFor(player);
        }
    }

    private void giveStartingCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCardsFor(player);
        }

        blackjackProcessManager.giveStartingCardsFor(dealer);

        OutputView.printStartingCardsStatuses(dealer, players);
    }

    private List<PlayerResult> getCardResultOfPlayer(Players players, Dealer dealer) {
        blackjackProcessManager.calculateCardResult(players, dealer, gameRuleEvaluator);
        return blackjackProcessManager.getPlayersResult();
    }

    private DealerResult getCardResultOfDealer(Dealer dealer) {
        return blackjackProcessManager.calculateDealerResult(dealer);
    }
}
