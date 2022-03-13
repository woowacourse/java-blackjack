package blackjack.controller;

import blackjack.domain.card.Status;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.RecordDto;
import blackjack.service.Casino;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameController {

    public void play() {
        final Casino casino = crateCasino();
        prepare(casino);

        progressPlayerTurns(casino);
        progressDealerTurn(casino);

        endGame(casino);
    }

    private Casino crateCasino() {
        try {
            return new Casino(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return crateCasino();
        }
    }

    private void prepare(final Casino casino) {
        casino.prepareParticipants();

        OutputView.printInitResult(casino.getPlayersNames());
        OutputView.printDealerFirstCard(casino.findDealerFirstCard());
        casino.findAllPlayers().forEach(OutputView::printPlayerCards);
    }

    private void progressPlayerTurns(final Casino casino) {
        while (casino.isPlayerTurn()) {
            final String playerName = casino.findDrawablePlayerName();
            final Status status = InputView.getHitOrStay(playerName);

            final ParticipantDto dto = casino.progressPlayerTurn(playerName, status);
            OutputView.printPlayerCards(dto);
        }
    }

    private void progressDealerTurn(final Casino casino) {
        final DealerTurnResultDto dealerTurnResultDto = casino.progressDealerTurn();
        OutputView.printDealerTurnResult(dealerTurnResultDto);
    }

    private void endGame(final Casino casino) {
        final List<ParticipantResultDto> resultDtos = casino.getAllResult();
        OutputView.breakLine();
        resultDtos.forEach(OutputView::printParticipantCards);

        final RecordDto recordDto = casino.getAllRecord();
        OutputView.printRecord(recordDto);
    }
}