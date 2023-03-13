package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResult;
import blackjack.dto.ChallengerResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.exception.CustomException;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    private static List<String> makeCardInfo(List<Card> inputCards) {
        List<String> cardInfo = new ArrayList<>();
        inputCards.stream()
                .forEach(card -> cardInfo.add(card.getSymbol().getName() + card.getShape().getName()));
        return cardInfo;
    }

    public void run() {
        init();
        start();
        takeTurn();
        showResult();
    }

    private void init() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            Players players = Players.from(playerNames);
            bet(players);
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            init();
        }
    }

    private void bet(Players players) {
        List<Challenger> challengers = players.getChallengers();
        List<Money> betAmounts = new ArrayList<>();
        challengers.stream()
                .forEach(challenger -> betEachPlayer(challenger, betAmounts));
        blackJackGame = BlackJackGame.from(players, betAmounts);
    }

    private void betEachPlayer(Challenger challenger, List<Money> betAmounts) {
        try {
            int inputMoney = InputView.inputPlayerBetMoney(challenger.getName());
            betAmounts.add(Money.bet(inputMoney));
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            betEachPlayer(challenger, betAmounts);
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
        List<Challenger> challengers = blackJackGame.getChallengers();
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
        showRevenue();
    }

    private void showPoint() {
        PlayerStatusDto dealerStatus = makeDealerStatus();
        List<PlayerStatusDto> challengersStatus = makeChallengersStatus();
        OutputView.printEndStatus(dealerStatus, challengersStatus);
    }

    private void showRevenue() {
        GameResult resultMap = blackJackGame.makeResult();

        ChallengerResultDto challengerResultDto = makeChallengerResultDto(resultMap, blackJackGame.getChallengers());
        OutputView.printRevenue(challengerResultDto);
    }

    private PlayerStatusDto makePlayerStatusDto(Player player) {
        String playerName = player.getName();
        List<Card> inputCards = player.getHoldingCards().getCards();
        int playerPoint = player.getTotalPoint();
        return new PlayerStatusDto(playerName, makeCardInfo(inputCards), playerPoint);
    }

    private ChallengerResultDto makeChallengerResultDto(GameResult gameResult, List<Challenger> challengers) {
        Map<String, Integer> nameAndResult = new LinkedHashMap<>();
        nameAndResult.put(Dealer.NAME, gameResult.getDealerRevenue().getValue());
        challengers.stream()
                .forEach(challenger -> nameAndResult.put(challenger.getName(),
                        gameResult.getChallengerRevenue(challenger).getValue()));
        return new ChallengerResultDto(nameAndResult);
    }
}
