package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.model.WinningDto;
import blackjack.domain.result.outcome.PlayersResults;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class WinOrLoseMode implements ModeStrategy<Player> {

    @Override
    public Players<Player> createPlayers(List<Name> names) {
        return PlayersFactory.createPlayers(names);
    }

    @Override
    public void showResult(Players<Player> players, Dealer dealer) {
        PlayersResults playersResults = new PlayersResults(players, dealer);

        List<WinningDto> playersDto = playersResults.stream()
                .map(result -> new WinningDto(result.getName(), result.getResultType()))
                .collect(Collectors.toList());

        OutputView.printFinalResult(dealer.name(), playersResults.computeDealerResult(), playersDto);
    }
}
