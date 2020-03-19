package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResponseDTO.ProfitDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BettingGame extends BlackJackController {

    @Override
    protected Players createPlayers() {
        List<Name> names = createNames();
        List<Money> bettingMoneys = createMoneys(names);
        return new Players(names, bettingMoneys);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private List<Money> createMoneys(List<Name> names) {
        return InputView.enterBettingMoney(names).stream()
                .map(Money::new)
                .collect(Collectors.toList());
    }

    @Override
    protected void showResult(Players players, Dealer dealer) {
        List<PlayerResult> playerResults = players.createPlayerResults(dealer);

        List<ProfitDTO> playerDTOS = playerResults.stream()
                .map(result -> new ProfitDTO(result.name(), result.computeProfit()))
                .collect(Collectors.toList());

        ProfitDTO dealerDTO = new ProfitDTO(dealer.name(), dealer.computeDealerProfit(playerResults));

        List<ProfitDTO> profitDTOS = new ArrayList<>();
        profitDTOS.add(dealerDTO);
        profitDTOS.addAll(playerDTOS);

        OutputView.printFinalProfit(profitDTOS);
    }
}
