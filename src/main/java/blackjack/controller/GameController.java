package blackjack.controller;

import blackjack.domain.card.Status;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.RecordDto;
import blackjack.service.GameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameController {

    private final GameService gameService;

    public GameController() {
        this.gameService = initService();
    }

    private GameService initService() {
        try {
            return new GameService(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initService();
        }
    }

    public void prepare() {
        gameService.prepareParticipants();

        OutputView.printInitResult(gameService.getPlayersNames());
        OutputView.printDealerFirstCard(gameService.findDealerFirstCard());
        gameService.findAllPlayers().forEach(OutputView::printPlayerCards);
    }

    public void progressPlayerTurns() {
        while (gameService.isPlayerTurn()) {
            final String playerName = gameService.findDrawablePlayerName();
            final Status status = InputView.getHitOrStay(playerName);

            final ParticipantDto dto = gameService.progressPlayerTurn(playerName, status);
            OutputView.printPlayerCards(dto);
        }
    }

    public void progressDealerTurn() {
        final DealerTurnResultDto dealerTurnResultDto = gameService.progressDealerTurn();
        OutputView.printDealerTurnResult(dealerTurnResultDto);
    }

    public void endGame() {
        final List<ParticipantResultDto> resultDtos = gameService.getAllResult();
        OutputView.breakLine();
        resultDtos.forEach(OutputView::printParticipantCards);

        final RecordDto recordDto = gameService.getAllRecord();
        OutputView.printRecord(recordDto);
    }
}