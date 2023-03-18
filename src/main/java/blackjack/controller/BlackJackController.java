package blackjack.controller;

import blackjack.domain.*;
import blackjack.dto.ParticipantsProfitDto;
import blackjack.dto.PersonStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class BlackJackController {

    private static final String DEALER_NAME = "딜러";

    private final BlackjackGame blackjackGame;

    public BlackJackController() {
        this.blackjackGame = setGame();
    }

    private BlackjackGame setGame() {
        Players players = repeat(() -> new Players(inputPlayersName()));
        BettingMoney bettingMoney = getBettingMoney(players.getPlayers());
        return new BlackjackGame(players, new DeckMaker(), bettingMoney);
    }

    private List<String> inputPlayersName() {
        String[] playersName = InputView.readPlayerNames();
        return new ArrayList<>(List.of(playersName));
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
        printGameResult(blackjackGame.getParticipantsProfitDto());
    }

    private BettingMoney getBettingMoney(List<Player> players) {
        Map<Player, Integer> bettingMoney = new HashMap<>();
        for (Player player : players) {
            int money = repeat(() -> validate(InputView.readBettingMoney(player.getName())));
            bettingMoney.put(player, money);
        }
        return new BettingMoney(bettingMoney);
    }

    private int validate(int value) {
        if (value > 0) {
            return value;
        }
        throw new IllegalArgumentException("[ERROR] 양의 정수만 입력 가능합니다.");
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
        while (getDecision(name)) {
            blackjackGame.drawMoreCard(name);
            PersonStatusDto personStatusDto = blackjackGame.getPlayerStatus(name);
            OutputView.printPersonStatus(personStatusDto.getName(), personStatusDto.getCards());

        }
        PersonStatusDto personStatusDto = blackjackGame.getPlayerStatus(name);
        OutputView.printPersonStatus(personStatusDto.getName(), personStatusDto.getCards());
    }

    private boolean getDecision(String name) {
        String decision = InputView.readDrawCardDecision(name);
        if (!("y".equals(decision)) && !("n".equals(decision))) {
            throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
        }
        return decision.equals("y");
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

    private void printGameResult(ParticipantsProfitDto participantsProfitDto) {
        Map<String, Double> participantsProfit = participantsProfitDto.getParticipantsProfit();

        OutputView.printGameEndMessage();
        for (String name : blackjackGame.getParticipantsName()) {
            OutputView.printProfitResult(name, participantsProfit.get(name));
        }
    }
}
