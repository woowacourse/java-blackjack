package blackjack.controller;

import blackjack.Blackjack;
import blackjack.Deck;
import blackjack.Players;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private void openInitialCards(Dealer dealer, Players players) {
        OutputView.printInitialDistributionEndMessage(dealer.getName(), players.getNames());
        OutputView.printDealerCards(dealer.getName(), dealer.pickOpenCards());
        for (Object player : players) {
            OutputView.printCards(((Player)player).getName(), ((Player)player).pickOpenCards(), true);
        }
    }

    private void distributeAdditionCardsToPlayer(Blackjack blackjack, Player player) {
        while (blackjack.isPossibleToGetCard(player) && InputView.askToGetAdditionCard(player.getName())) {
            blackjack.distributeAdditionalCard(player);
            OutputView.printCards(player.getName(), player.getCards(), true);
        }
    }

    private void distributeAdditionCardsToAllPlayer(Blackjack blackjack, Players players) {
        for (Object player : players) {
            distributeAdditionCardsToPlayer(blackjack, (Player)player);
        }
    }

    private void openCardsWithScore(Dealer dealer, Players players) {
        OutputView.printCards(dealer.getName(), dealer.getCards(), false);
        OutputView.printScore(dealer.score());
        for (Object obj : players) {
            Player player = (Player) obj;
            OutputView.printCards(player.getName(), player.getCards(), false);
            OutputView.printScore(player.score());
        }
    }

    private void distributeAdditionCardsToDealer(Blackjack blackjack, Dealer dealer) {
        while (blackjack.isPossibleToGetCard(dealer)) {
            blackjack.distributeAdditionalCard(dealer);
            OutputView.printDealerAdditionalCardMessage();
        }
    }

    public void run() {
        Blackjack blackjack = Blackjack.generate();
        Players players = Players.generateWithNames(InputView.enterPlayerNames());
        Dealer dealer = Dealer.generate();

        blackjack.distributeInitCards(dealer, players);
        openInitialCards(dealer, players);

        distributeAdditionCardsToAllPlayer(blackjack, players);
        distributeAdditionCardsToDealer(blackjack, dealer);

        openCardsWithScore(dealer, players);
    }
}
