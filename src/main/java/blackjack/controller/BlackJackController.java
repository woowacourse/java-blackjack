package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.result.Result;
import blackjack.domain.result.Results;
import blackjack.dto.ChallengerResultDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        List<PlayerStatusDto> challengersStatus = makeChallengersStatus();
        OutputView.printStartStatus(dealerStatus, challengersStatus);
    }

    private PlayerStatusDto makeDealerStatus() {
        Player dealer = blackJackGame.getDealer();
        return makePlayerStatusDto(dealer);
    }

    private List<PlayerStatusDto> makeChallengersStatus() {
        List<Player> challengers = blackJackGame.getChallengers();
        return challengers.stream()
                .map(challenger -> makePlayerStatusDto(challenger))
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
            OutputView.printChallengerStatusInGame(makePlayerStatusDto(player));
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
        PlayerStatusDto dealerStatus = makeDealerStatus();
        List<PlayerStatusDto> challengersStatus = makeChallengersStatus();
        OutputView.printEndStatus(dealerStatus, challengersStatus);
    }

    private void showRank() {
        Results results = blackJackGame.makeResult();

        ChallengerResultDto challengerResultDto = makeChallengerResultDto(results, blackJackGame.getChallengers());
        DealerResultDto dealerResultDto = makeDealerResultDto(results, blackJackGame.getDealer());
        OutputView.printFinalRank(challengerResultDto, dealerResultDto);
    }

    private PlayerStatusDto makePlayerStatusDto(Player player) {
        String playerName = player.getName();
        List<Card> inputCards = player.getHoldingCards().getCards();
        int playerPoint = player.getTotalPoint();
        return new PlayerStatusDto(playerName, makeCardInfo(inputCards), playerPoint);
    }

    private static List<String> makeCardInfo(List<Card> inputCards) {
        List<String> cardInfo = new ArrayList<>();
        for (Card card : inputCards) {
            cardInfo.add(card.getSymbol().getName() + card.getShape().getName());
        }
        return cardInfo;
    }

    private ChallengerResultDto makeChallengerResultDto(Results results, List<Player> challengers) {
        Map<String, String> nameAndRanks = new LinkedHashMap<>();
        for (Player challenger : challengers) {
            Result challengerResult = results.getChallengerResult(challenger);
            nameAndRanks.put(challenger.getName(), challengerResult.getLabel());
        }
        return new ChallengerResultDto(nameAndRanks);
    }

    private DealerResultDto makeDealerResultDto(Results results, Player dealer) {
        String dealerName = dealer.getName();
        Map<Result, Integer> dealerResult = results.getDealerResult();
        int winCount = dealerResult.getOrDefault(Result.WIN, 0);
        int drawCount = dealerResult.getOrDefault(Result.DRAW, 0);
        int loseCount = dealerResult.getOrDefault(Result.LOSE, 0);
        return new DealerResultDto(dealerName, winCount, drawCount, loseCount);
    }
}
