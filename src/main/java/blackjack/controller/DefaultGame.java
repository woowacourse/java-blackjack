package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGame extends BlackJackController {

    @Override
    protected Players createPlayers() {
        List<Name> names = createNames();
        return new Players(names);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    @Override
    protected void showResult(Players players, Dealer dealer) {
        List<PlayerResult> playerResults = players.createPlayerResults(dealer);
        DealerResult dealerResult = dealer.createDealerResult(playerResults);

        OutputView.printFinalResult(dealerResult, playerResults);
    }
}
