package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultMap;
import blackjack.domain.result.ResultType;
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
        if (player.canPick()) {
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
            blackJackGame.hit(player);
            OutputView.printChallengerStatusInGame(makePlayerStatusDto(player));
            takeEachChallengerTurn(player);
        }
    }

    private void takeDealerTurn() {
        Player dealer = blackJackGame.getDealer();
        boolean dealerCanPick = dealer.canPick();
        if (dealerCanPick) {
            blackJackGame.hit(dealer);
        }
        OutputView.printDealerTurnResult(dealerCanPick, Dealer.MAXIMUM_POINT);
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
        ResultMap resultMap = blackJackGame.makeResult();

        ChallengerResultDto challengerResultDto = makeChallengerResultDto(resultMap, blackJackGame.getChallengers());
        DealerResultDto dealerResultDto = makeDealerResultDto(resultMap, blackJackGame.getDealer());
        OutputView.printEndRank(challengerResultDto, dealerResultDto);
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

    private ChallengerResultDto makeChallengerResultDto(ResultMap resultMap, List<Player> challengers) {
        Map<String, String> nameAndResult = new LinkedHashMap<>();
        for (Player challenger : challengers) {
            ResultType challengerResultType = resultMap.getChallengerResult(challenger);
            nameAndResult.put(challenger.getName(), challengerResultType.getLabel());
        }
        return new ChallengerResultDto(nameAndResult);
    }

    private DealerResultDto makeDealerResultDto(ResultMap resultMap, Player dealer) {
        String dealerName = dealer.getName();
        Map<ResultType, Integer> dealerResult = resultMap.getDealerResult();
        int winCount = dealerResult.getOrDefault(ResultType.WIN, 0);
        int drawCount = dealerResult.getOrDefault(ResultType.DRAW, 0);
        int loseCount = dealerResult.getOrDefault(ResultType.LOSE, 0);
        return new DealerResultDto(dealerName, winCount, drawCount, loseCount);
    }
}
