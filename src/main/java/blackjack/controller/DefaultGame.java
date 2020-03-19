package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGame extends BlackJackController {

    @Override
    public void run() {
        List<Name> names = createNames();

        Players players = new Players(names);
        Dealer dealer = new Dealer();

        dealFirstCards(dealer, players);

        dealAdditionalCards(players, dealer);

        showResult(players, dealer);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void dealFirstCards(Dealer dealer, Players players) {
        deck.dealFirstCards(dealer);

        for (Player player : players.getPlayers()) {
            deck.dealFirstCards(player);
        }

        OutputView.printInitialStatus(players, dealer);
    }

    private void dealAdditionalCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            dealAdditionalCards(player);
        }

        dealAdditionalCards(dealer);

        OutputView.printFinalStatus(players, dealer);
    }

    private void dealAdditionalCards(Player player) {
        while (player.canGetMoreCard() && player.wantMoreCard(readYesOrNo(player))) {
            deck.dealAdditionalCard(player);
            OutputView.printCardsStatus(player.name(), player.showCards());
        }
    }

    private void dealAdditionalCards(Dealer dealer) {
        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard();
        }
    }

    private String readYesOrNo(Player player) {
        return InputView.readYesOrNo(player.name());
    }

    private void showResult(Players players, Dealer dealer) {
        List<PlayerResult> playerResults = players.createPlayerResults(dealer);
        DealerResult dealerResult = dealer.createDealerResult(playerResults);

        OutputView.printFinalResult(dealerResult, playerResults);
    }
}
