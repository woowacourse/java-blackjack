package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.ResultDto;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackJackResultManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;
    private final BlackJackResultManager blackJackResultManager;

    public BlackjackController(BlackJackInitManager blackJackInitManager) {
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck());
        this.blackJackResultManager = new BlackJackResultManager();
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = blackJackInitManager.generatePlayers(names);
        Dealer dealer = blackJackInitManager.generateDealer();

        giveStartingCards(players, dealer);

        giveMoreCardFor(players);
        giveMoreCardFor(dealer);

        printResult(players, dealer);
    }

    private void giveMoreCardFor(Dealer dealer) {
        while (dealer.canTakeCard()) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(dealer);
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

        blackjackProcessManager.giveCard(player);
        OutputView.printCardResult(player);

        if (player.isBusted()) {
            OutputView.printBustedPlayer(player);
            return;
        }

        if (player.canTakeCard()) {
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

    private void printResult(Players players, Dealer dealer) {
        ResultDto resultDto = blackJackResultManager.calculateCardResult(players, dealer);
        OutputView.printCardResult(players, dealer);
        OutputView.printGameResult(resultDto.dealerResult(),
                resultDto.playersResult());
    }
}
