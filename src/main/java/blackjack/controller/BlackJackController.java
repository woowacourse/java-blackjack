package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.player.Player;
import blackjack.domain.result.Result;
import blackjack.dto.ChallengerResultDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.DealerStatusDto;
import blackjack.dto.DealerStatusWithPointDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

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
        DealerStatusDto dealerStatus = makeDealerStatus();
        List<PlayerStatusDto> challengersStatus = makeChallengersStatus();
        OutputView.printStartStatus(dealerStatus, challengersStatus);
    }

    private DealerStatusDto makeDealerStatus() {
        Player dealer = blackJackGame.getDealer();
        return DealerStatusDto.from(dealer);
    }

    private List<PlayerStatusDto> makeChallengersStatus() {
        List<Player> players = blackJackGame.getChallengers();
        List<PlayerStatusDto> gameStatus = new ArrayList<>();
        for (Player player : players) {
            PlayerStatusDto playerStatusDto = new PlayerStatusDto(player);
            gameStatus.add(playerStatusDto);
        }
        return gameStatus;
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
            OutputView.printChallengerStatusInGame(new PlayerStatusDto(player));
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
        DealerStatusWithPointDto dealerStatus = makeDealerWithPointStatus();
        List<PlayerStatusDto> challengersStatus = makeChallengersStatus();
        OutputView.printEndStatus(dealerStatus, challengersStatus);
    }

    private DealerStatusWithPointDto makeDealerWithPointStatus() {
        Player dealer = blackJackGame.getDealer();
        return DealerStatusWithPointDto.from(dealer);
    }

    private void showRank() {
        Result result = blackJackGame.makeResult();
        ChallengerResultDto challengerResultDto = new ChallengerResultDto(result, blackJackGame.getChallengers());
        DealerResultDto dealerResultDto = new DealerResultDto(result, blackJackGame.getDealer());
        OutputView.printFinalRank(challengerResultDto, dealerResultDto);
    }
}
