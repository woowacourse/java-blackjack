package blackjack.controller;

import blackjack.dto.PersonStatusResponse;
import blackjack.dto.PersonTotalStatusResponse;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        repeat(() -> blackJackService.createPeople(InputView.readPlayerNames()));
        OutputView.printStartDrawCardMessage(blackJackService.getPlayersName());
        printPersonStatues(blackJackService.getStartStatusResponse());
        drawMoreCardForPlayers();
        OutputView.printDealerDrawCardMessage(blackJackService.drawMoreCardForDealer());
        printPersonTotalStatues(blackJackService.getAllPersonTotalResponse());
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

    private void printPersonStatues(List<PersonStatusResponse> personStatusResponse) {
        for (PersonStatusResponse response : personStatusResponse) {
            OutputView.printPersonStatus(response);
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
            OutputView.printPersonStatus(blackJackService.getPersonStatusResponseByName(playerName));
        }
        OutputView.printPersonStatus(blackJackService.getPersonStatusResponseByName(playerName));
    }

    private boolean decideDraw(String playerName) {
        return InputView.readDrawCardDecision(playerName);
    }

    private void printPersonTotalStatues(List<PersonTotalStatusResponse> responses) {
        for (PersonTotalStatusResponse response : responses) {
            OutputView.printPersonTotalStatus(response);
        }
    }
}
