package blackjack.controller;

import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
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
        repeat(() -> blackJackService.setupGame(deckGenerator, InputView.readPlayerNames()));
        OutputView.printStartDrawCardMessage(blackJackService.getPlayersName());
        printAllParticipantStatues(blackJackService.getStartStatusResponse());
        drawMoreCardForPlayers();
        OutputView.printDealerDrawCardMessage(blackJackService.drawMoreCardForDealer());
        printAllTotalStatues(blackJackService.getAllParticipantTotalResponse());
        OutputView.printTotalGameResult(blackJackService.getTotalGameResult());
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

    private void drawMoreCardForPlayers() {
        for (String playerName : blackJackService.getPlayersName()) {
            repeat(() -> drawMoreCard(playerName));
        }
    }

    private void drawMoreCard(String playerName) {
        while (decideDraw(playerName)) {
            blackJackService.drawMoreCardByName(playerName);
            OutputView.printParticipantStatus(blackJackService.getParticipantStatusResponseByName(playerName));
        }
        OutputView.printParticipantStatus(blackJackService.getParticipantStatusResponseByName(playerName));
    }

    private boolean decideDraw(String playerName) {
        return InputView.readDrawCardDecision(playerName);
    }

    private void printAllTotalStatues(List<ParticipantTotalStatusResponse> responses) {
        for (ParticipantTotalStatusResponse response : responses) {
            OutputView.printParticipantTotalStatus(response);
        }
    }
}
