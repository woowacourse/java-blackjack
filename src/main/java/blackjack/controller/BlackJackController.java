package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.player.Player;
import blackjack.domain.result.Result;
import blackjack.dto.ChallengerResultDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerStatusWithPointDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        init();
        start();
        takeTurn();
        showResult();
        InputView.terminate();
    }

    private void init() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            blackJackGame = BlackJackGame.from(playerNames);
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            init();
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
        List<Player> challengers = blackJackGame.getChallengers();
        return challengers.stream()
                .map(PlayerStatusDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void takeTurn() {
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

    private void takeEachChallengerTurn(Player player) {
        if (blackJackGame.canPick(player)) {
            checkChoice(player);
        }
    }

    private void checkChoice(Player player) {
        try {
            inputChoice(player);
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            checkChoice(player);
        }
    }

    private void inputChoice(Player player) {
        boolean choice = InputView.inputPlayerChoice(player.getName());
        if (choice) {
            blackJackGame.pick(player);
            OutputView.printChallengerStatusInGame(PlayerStatusDto.from(player));
            takeEachChallengerTurn(player);
        }
    }

    private void takeDealerTurn() {
        Player dealer = blackJackGame.getDealer();
        boolean dealerCanPick = dealer.canPick();
        if (dealerCanPick) {
            blackJackGame.pick(dealer);
        }
        OutputView.printDealerResult(dealerCanPick);
    }

    private void showResult() {
        showPoint();
        showRank();
    }

    private void showPoint() {
        PlayerStatusWithPointDto dealerStatus = makeDealerWithPointStatus();
        List<PlayerStatusWithPointDto> challengersStatus = makeChallengersWithPointStatus();
        OutputView.printEndStatus(dealerStatus, challengersStatus);
    }

    private PlayerStatusWithPointDto makeDealerWithPointStatus() {
        Player dealer = blackJackGame.getDealer();
        return PlayerStatusWithPointDto.from(dealer);
    }

    private List<PlayerStatusWithPointDto> makeChallengersWithPointStatus() {
        List<Player> challengers = blackJackGame.getChallengers();
        return challengers.stream()
                .map(PlayerStatusWithPointDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void showRank() {
        Result result = blackJackGame.makeResult();
        ChallengerResultDto challengerResultDto = new ChallengerResultDto(result, blackJackGame.getChallengers());
        DealerResultDto dealerResultDto = new DealerResultDto(result, blackJackGame.getDealer());
        OutputView.printFinalRank(challengerResultDto, dealerResultDto);
    }
}
