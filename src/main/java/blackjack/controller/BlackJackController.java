package blackjack.controller;

import blackjack.domain.Name;
import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerNamesResponse;
import blackjack.service.BlackJackService;
import blackjack.service.DeckGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run(DeckGenerator deckGenerator) {
        setupGame(deckGenerator);
        playGame();
        finishGame();
    }

    private void setupGame(DeckGenerator deckGenerator) {
        repeat(() -> blackJackService.setupGame(deckGenerator, InputView.readPlayerNames()));
        OutputView.printStartDrawCardMessage(PlayerNamesResponse.of(blackJackService.getPlayersName()));
        printAllParticipantStatues(blackJackService.getStartStatusResponse());
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void printAllParticipantStatues(List<ParticipantStatusResponse> participantStatusResponse) {
        for (ParticipantStatusResponse response : participantStatusResponse) {
            OutputView.printParticipantStatus(response);
        }
    }

    private void playGame() {
        drawMoreCardForPlayers();
        OutputView.printDealerDrawCardMessage(blackJackService.drawMoreCardForDealer());
    }

    private void drawMoreCardForPlayers() {
        for (Name playerName : blackJackService.getPlayersName()) {
            repeat(() -> drawMoreCard(playerName));
        }
    }

    private void drawMoreCard(Name playerName) {
        while (decideDraw(playerName)) {
            blackJackService.drawMoreCardByName(playerName);
            OutputView.printParticipantStatus(blackJackService.getParticipantStatusResponseByName(playerName));
        }
        OutputView.printParticipantStatus(blackJackService.getParticipantStatusResponseByName(playerName));
    }

    private boolean decideDraw(Name playerName) {
        return InputView.readDrawCardDecision(playerName.getName());
    }

    private void finishGame() {
        printAllTotalStatues(blackJackService.getAllParticipantTotalResponse());
        OutputView.printTotalGameResult(blackJackService.getTotalGameResult());
    }

    private void printAllTotalStatues(List<ParticipantTotalStatusResponse> responses) {
        for (ParticipantTotalStatusResponse response : responses) {
            OutputView.printParticipantTotalStatus(response);
        }
    }
}
