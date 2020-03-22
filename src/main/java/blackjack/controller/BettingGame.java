package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.PlayersResults;
import blackjack.domain.result.responseDto.ProfitDto;
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
        return PlayersFactory.createBettingPlayers(names, bettingMoneys);
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
        PlayersResults playersResults = players.createPlayerResults(dealer);

        ProfitDto dealerDto = new ProfitDto(dealer.name(), playersResults.computeDealerProfit());

        List<ProfitDto> playerDtos = playersResults.stream()
                .map(result -> new ProfitDto(result.name(), result.computeProfit()))
                .collect(Collectors.toList());

        List<ProfitDto> profitDtos = new ArrayList<>();
        profitDtos.add(dealerDto);
        profitDtos.addAll(playerDtos);

        OutputView.printFinalProfit(profitDtos);
    }
}
