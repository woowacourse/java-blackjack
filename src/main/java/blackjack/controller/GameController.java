package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Status;
import blackjack.dto.DealerRecordDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.PlayerRecordDto;
import blackjack.service.DealerService;
import blackjack.service.PlayerService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameController {

    private final PlayerService playerService;
    private final DealerService dealerService;

    public GameController() {
        final Deck deck = Deck.create();
        this.playerService = initPlayerService(deck);
        this.dealerService = new DealerService(deck);
    }

    private PlayerService initPlayerService(final Deck deck) {
        try {
            return new PlayerService(deck, InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initPlayerService(deck);
        }
    }

    public void prepare() {
        dealerService.prepare();
        playerService.prepare();

        OutputView.printPrepareResult(playerService.findAllNames());
        OutputView.printDealerFirstCard(dealerService.findFirstCard());
        playerService.findAllPlayers().forEach(OutputView::printPlayerCards);
    }

    public void progressPlayerTurns() {
        while (playerService.isPlayerTurn()) {
            final String playerName = playerService.findDrawablePlayerName();
            final Status status = InputView.getHitOrStay(playerName);

            final ParticipantDto dto = playerService.progressTurn(playerName, status);
            OutputView.printPlayerCards(dto);
        }
    }

    public void progressDealerTurn() {
        final DealerTurnResultDto dto = dealerService.progressTurn();
        OutputView.printDealerTurnResult(dto);
    }

    public void endGame() {
        getAllCards();
        getAllRecord();
    }

    private void getAllCards() {
        final List<ParticipantResultDto> dto = playerService.findAllResult();
        dto.add(0, dealerService.getResult());

        OutputView.breakLine();
        dto.forEach(OutputView::printParticipantCards);
    }

    private void getAllRecord() {
        final List<PlayerRecordDto> playerRecordDtos = playerService.getRecord(dealerService.getScore());
        final DealerRecordDto dealerRecordDto = DealerRecordDto.of(playerRecordDtos);

        OutputView.printDealerRecord(dealerRecordDto);
        playerRecordDtos.forEach(OutputView::printPlayerRecord);
    }
}