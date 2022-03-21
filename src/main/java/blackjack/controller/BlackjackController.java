package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Players;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Blackjack blackjack = Blackjack.generate();
        Players players = Players.generateWithNames(InputView.enterPlayerNames());
        Dealer dealer = Dealer.generate();
        betting(blackjack, players);

        blackjack.distributeInitCards(dealer, players);
        openInitialCards(dealer, players);
        if (blackjack.gameOverByBlackjack(dealer)) {
            OutputView.result(blackjack.calculateYield(dealer, players));
            return;
        }

        distributeAdditionCardsToAllPlayer(blackjack, players);
        distributeAdditionCardsToDealer(blackjack, dealer);

        openCardsWithScore(dealer, players);
        OutputView.result(blackjack.calculateYield(dealer, players));
    }

    private void openInitialCards(Participant dealer, Players players) {
        OutputView.printInitialDistributionEndMessage(dealer.getName(), players.getNames());
        OutputView.printDealerCards(dealer.getName(), dealer.pickOpenCards());
        for (Participant player : players) {
            OutputView.printCards(player.getName(), player.pickOpenCards(), true);
        }
    }

    private void distributeAdditionCardsToPlayer(Blackjack blackjack, Participant player) {
        while (blackjack.isPossibleToGetCard(player) && InputView.askToGetAdditionCard(player.getName())) {
            blackjack.distributeAdditionalCard(player);
            OutputView.printCards(player.getName(), player.getCards(), true);
        }
        player.setStateStayIfSatisfied(true);
    }

    private void distributeAdditionCardsToAllPlayer(Blackjack blackjack, Players players) {
        for (Participant player : players) {
            distributeAdditionCardsToPlayer(blackjack, player);
        }
    }

    private void openCardsWithScore(Participant dealer, Players players) {
        OutputView.printCards(dealer.getName(), dealer.getCards(), false);
        OutputView.printScore(dealer.score());
        for (Participant player : players) {
            OutputView.printCards(player.getName(), player.getCards(), false);
            OutputView.printScore(player.score());
        }
    }

    private void distributeAdditionCardsToDealer(Blackjack blackjack, Participant dealer) {
        while (blackjack.isPossibleToGetCard(dealer)) {
            blackjack.distributeAdditionalCard(dealer);
            OutputView.printDealerAdditionalCardMessage();
        }
    }

    private void betting(Blackjack blackjack, Players players) {
        for (Participant player : players) {
            blackjack.betting(player, InputView.askBettingMoney(player.getName()));
        }
    }
}
