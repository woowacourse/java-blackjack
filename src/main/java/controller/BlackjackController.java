package controller;

import domain.Answer;
import domain.BlackJackRule;
import domain.UserFactory;
import domain.WinningResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.player.Player;
import dto.*;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    private static BlackJackRule blackJackRule = new BlackJackRule();

    private BlackjackController() {
    }

    public static void run() {
        CardDeck cardDeck = new CardDeck();

        Map<String, Integer> bettingMoneyToName = getBettingMoneyToName();
        Users users = UserFactory.create(cardDeck, bettingMoneyToName);
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(users);
        OutputView.printInitialResult(responsePlayerDTOS);

        runPlayerBlackJack(cardDeck, users.getPlayer());
        runDealerBlackJack(cardDeck, users.getDealer());
        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(users));

        WinningResult winningResult = WinningResult.create(users);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printFinalProfit(responseWinningResultDTO);
    }

    private static Map<String, Integer> getBettingMoneyToName() {
        List<RequestPlayerDTO> requestPlayerDTOS = InputView.inputPlayer();

        return requestPlayerDTOS.stream()
                .collect(Collectors.toMap(RequestPlayerDTO::getName, RequestPlayerDTO::getBettingMoney,
                        (x, y) -> y, LinkedHashMap::new));
    }

    private static void runPlayerBlackJack(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            runBlackJackPerPlayer(cardDeck, player);
        }
    }

    private static void runBlackJackPerPlayer(CardDeck cardDeck, Player player) {
        while (blackJackRule.isHit(player) && getAnswer(player).isYes()) {
            blackJackRule.hit(player, cardDeck.drawCard());
            OutputView.printUserCard(ResponsePlayerDTO.create(player));
        }
    }

    private static void runDealerBlackJack(CardDeck cardDeck, Dealer dealer) {
        int dealerHitCount = 0;
        while (blackJackRule.isHit(dealer)) {
            dealerHitCount = dealerHitCount + 1;
            blackJackRule.hit(dealer, cardDeck.drawCard());
        }
        OutputView.printDealerAdditionalCard(dealerHitCount);
    }

    private static Answer getAnswer(User user) {
        try {
            RequestAnswerDTO requestAnswerDTO = InputView.inputAnswer(ResponsePlayerDTO.create(user));
            return Answer.of(requestAnswerDTO.getAnswer());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getAnswer(user);
        }
    }
}
