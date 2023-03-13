package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.ChallengerName;
import blackjack.domain.player.ChallengerNames;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;
import blackjack.dto.AllPlayersStatusWithPointDto;
import blackjack.dto.ChallengerNameAndMoneyDto;
import blackjack.dto.ProfitDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        init();
        start();
        takeTurnWhenNotBlackjack();
        showResult();
        InputView.terminate();
    }

    private void init() {
        ChallengerNames challengerNames = initChallengerNames();
        List<ChallengerNameAndMoneyDto> challengerNameAndMoneyDtos = makeChallengerMoneyDtos(challengerNames);
        blackJackGame = BlackJackGame.from(challengerNameAndMoneyDtos);
    }

    private ChallengerNames initChallengerNames() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            return ChallengerNames.from(playerNames);
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            return initChallengerNames();
        }
    }

    private List<ChallengerNameAndMoneyDto> makeChallengerMoneyDtos(final ChallengerNames challengerNames) {
        List<ChallengerNameAndMoneyDto> challengerNameAndMoneyDtos = new ArrayList<>();
        for (ChallengerName name : challengerNames.getChallengerNames()) {
            challengerNameAndMoneyDtos.add(makeChallengerMoneyDto(name));
        }
        return challengerNameAndMoneyDtos;
    }

    private ChallengerNameAndMoneyDto makeChallengerMoneyDto(final ChallengerName name) {
        try {
            int money = InputView.inputMoney(name.getName());
            return new ChallengerNameAndMoneyDto(name, Money.from(money));
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            return makeChallengerMoneyDto(name);
        }
    }

    private void start() {
        blackJackGame.handOutStartCards();
        showStartStatus();
    }

    private void showStartStatus() {
        PlayerStatusDto dealerStatus = makeDealerStatus();
        List<PlayerStatusDto> challengersStatus = makeChallengersStartStatus();
        OutputView.printStartStatus(dealerStatus, challengersStatus);
    }

    private PlayerStatusDto makeDealerStatus() {
        Player dealer = blackJackGame.getDealer();
        return PlayerStatusDto.from(dealer);
    }

    private List<PlayerStatusDto> makeChallengersStartStatus() {
        List<Challenger> challengers = blackJackGame.getChallengers();
        return challengers.stream()
                .map(PlayerStatusDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void takeTurnWhenNotBlackjack() {
        try {
            takeAllChallengersTurn();
            takeDealerTurn();
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
        }
    }

    private void takeAllChallengersTurn() {
        for (Player player : blackJackGame.getChallengers()) {
            takeEachChallengerTurn(player);
        }
    }

    private void takeEachChallengerTurn(final Player player) {
        if (blackJackGame.isPlayerBlackjack(player)) {
            printBlackjackMessage(player);
            return;
        }
        takeTurnWhenNotBlackjack(player);
    }

    private void printBlackjackMessage(final Player player) {
        OutputView.printBlackjackMessage(player.getName());
        OutputView.printChallengerStatusInGame(PlayerStatusDto.from(player));
    }

    private void takeTurnWhenNotBlackjack(Player player) {
        while (blackJackGame.canPick(player) && chooseGonnaPick(player)) {
            blackJackGame.pick(player);
            OutputView.printChallengerStatusInGame(PlayerStatusDto.from(player));
        }
        if (blackJackGame.canPick(player)) {
            OutputView.printChallengerStatusInGame(PlayerStatusDto.from(player));
        }
    }

    private boolean chooseGonnaPick(final Player player) {
        try {
            return InputView.inputPlayerChoice(player.getName());
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            return chooseGonnaPick(player);
        }
    }

    private void takeDealerTurn() {
        boolean isDealerPicked = blackJackGame.isDealerCanPick();
        blackJackGame.takeDealerTurn();
        OutputView.printDealerResult(isDealerPicked);
    }

    private void showResult() {
        showPoint();
        showProfits();
    }

    private void showPoint() {
        AllPlayersStatusWithPointDto allPlayersStatusWithPointDto = blackJackGame.getFinalResult();
        OutputView.printEndStatus(allPlayersStatusWithPointDto);
    }

    private void showProfits() {
        ProfitDto profitDto = blackJackGame.calculateProfit();
        OutputView.printProfits(profitDto);
    }
}
