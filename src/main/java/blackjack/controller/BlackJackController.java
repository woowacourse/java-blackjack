package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.DeckMaker;
import blackjack.dto.GameResultDto;
import blackjack.dto.PersonStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class BlackJackController {

    private static final String DEALER_NAME = "딜러";

    private final BlackjackGame blackjackGame;

    public BlackJackController() {
        this.blackjackGame = repeat(() -> new BlackjackGame(InputView.readPlayerNames(), new DeckMaker()));
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    public void run() {
        blackjackGame.drawInitCard();
        printInitStatus(blackjackGame.getParticipantsInit());
        drawMoreCardForPlayers(blackjackGame.getPlayersName());
        InputView.closeScanner();
        drawMoreCardForDealer();
        printAllStatus();
        printGameResult();
    }

    private void printInitStatus(List<PersonStatusDto> personStatusDtos) {
        List<String> playersName = personStatusDtos.stream()
                .map(PersonStatusDto::getName)
                .collect(toList());
        playersName.remove(DEALER_NAME);
        OutputView.printDefaultDrawCardMessage(playersName);

        for (PersonStatusDto personStatusDto : personStatusDtos) {
            OutputView.printPersonStatus(personStatusDto.getName(), personStatusDto.getCards());
        }
    }

    private void drawMoreCardForPlayers(List<String> playersName) {
        for (String name : playersName) {
            repeat(() -> getDecisionRepeatedly(name));
        }
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void getDecisionRepeatedly(String name) {
        while(getDecision(name)) {
            blackjackGame.drawMoreCard(name);
            PersonStatusDto personStatusDto = blackjackGame.getPlayerStatus(name);
            OutputView.printPersonStatus(personStatusDto.getName(), personStatusDto.getCards());

        }
        PersonStatusDto personStatusDto = blackjackGame.getPlayerStatus(name);
        OutputView.printPersonStatus(personStatusDto.getName(), personStatusDto.getCards());
    }

    private boolean getDecision(String name) {
        String decision = InputView.readDrawCardDecision(name);
        if (decision.equals("y") || decision.equals("n")) {
            return decision.equals("y");
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
    }

    private void drawMoreCardForDealer() {
        OutputView.printDealerDrawCardMessage(blackjackGame.getDealerScore());
        blackjackGame.drawDealerMoreCard();
    }

    private void printAllStatus() {
        for (PersonStatusDto personStatusDto : blackjackGame.getAllPersonStatus()) {
            printPersonStatus(personStatusDto);
        }
    }

    private void printPersonStatus(PersonStatusDto personStatusDto) {
        String name = personStatusDto.getName();
        int score = blackjackGame.getPlayerScore(name);
        OutputView.printPersonStatus(name, personStatusDto.getCards(), score);
    }

    private void printGameResult() {
        OutputView.printGameEndMessage();

        GameResultDto dealerGameResultDto = blackjackGame.getDelearGameResultDto();
        OutputView.printDealerResult(dealerGameResultDto.getDealerGameResult());

        for (String name : blackjackGame.getPlayersName()) {
            GameResultDto playerGameResultDto = blackjackGame.getPlayerGameResultDto(name);
            OutputView.printPlayerResult(name, playerGameResultDto.getPlayerGameResult());
        }
    }
}
