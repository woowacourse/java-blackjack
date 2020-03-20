package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.PlayersResults;
import blackjack.domain.result.ResponseDTO.WinningDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
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
        PlayersResults playersResults = players.createPlayerResults(dealer);

        List<WinningDTO> playersDTO = playersResults.getPlayersResults().stream()
                .map(result -> new WinningDTO(result.name(), result.resultType()))
                .collect(Collectors.toList());

        List<WinningDTO> winningDTOS = new ArrayList<>();
        WinningDTO dealerDTO = new WinningDTO(dealer.name(), playersResults.showDealerRecord());
        winningDTOS.add(dealerDTO);
        winningDTOS.addAll(playersDTO);

        OutputView.printFinalResult(winningDTOS);
    }
}
