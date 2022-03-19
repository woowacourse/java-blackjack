package blackjack.controller;

import blackjack.Blackjack;
import blackjack.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private void openInitialCards(Blackjack blackjack) {
        OutputView.printInitialDistributionEndMessage(blackjack.dealer().getName(), blackjack.players().getNames());
        OutputView.printDealerCards(blackjack.dealer().getName(), blackjack.dealer().pickOpenCards());
        for (Object player : blackjack.players()) {
            OutputView.printCards(((Player)player).getName(), ((Player)player).pickOpenCards(), true);
        }
    }

    private void distributeAdditionCardsToPlayer(Blackjack blackjack, Player player) {
        while (blackjack.isPossibleToGetCard(player) && InputView.askToGetAdditionCard(player.getName())) {
            blackjack.distributeAdditionalCard(player);
            OutputView.printCards(player.getName(), player.getCards(), true);
        }
    }

    private void distributeAdditionCardsToAllPlayer(Blackjack blackjack) {
        for (Object player : blackjack.players()) {
            distributeAdditionCardsToPlayer(blackjack, (Player)player);
        }
    }

    private void openCardsWithScore(Blackjack blackjack) {
        OutputView.printCards(blackjack.dealer().getName(), blackjack.dealer().getCards(), false);
        OutputView.printScore(blackjack.dealer().score());
        for (Object obj : blackjack.players()) {
            Player player = (Player) obj;
            OutputView.printCards(player.getName(), player.getCards(), false);
            OutputView.printScore(player.score());
        }
    }

    private void distributeAdditionCardsToDealer(Blackjack blackjack) {
        while (blackjack.isPossibleToGetCard(blackjack.dealer())) {
            blackjack.distributeAdditionalCard(blackjack.dealer());
            OutputView.printDealerAdditionalCardMessage();

        }
    }

    public void run() {
        Blackjack blackjack = Blackjack.generate(InputView.enterPlayerNames());

        blackjack.distributeInitCards();
        openInitialCards(blackjack);

        distributeAdditionCardsToAllPlayer(blackjack);
        distributeAdditionCardsToDealer(blackjack);

        openCardsWithScore(blackjack);
    }
}
