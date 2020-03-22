package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.PlayersResults;
import blackjack.domain.result.responseDto.WinningDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultGame extends BlackJackController {

    @Override
    protected Players createPlayers() {
        List<Name> names = createNames();
        return PlayersFactory.createPlayers(names);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    @Override
    protected void showResult(Players players, Dealer dealer) {
        PlayersResults playersResults = players.createPlayerResults(dealer);

        List<WinningDto> playersDto = playersResults.stream()
                .map(result -> new WinningDto(result.name(), result.resultType()))
                .collect(Collectors.toList());

        List<WinningDto> winningDtos = new ArrayList<>();
        WinningDto dealerDto = new WinningDto(dealer.name(), playersResults.showDealerRecord());
        winningDtos.add(dealerDto);
        winningDtos.addAll(playersDto);

        OutputView.printFinalResult(winningDtos);
    }
}
