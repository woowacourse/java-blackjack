package controller;

import domain.Answer;
import domain.BlackJackRule;
import domain.PlayerFactory;
import domain.WinningResult;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.player.Player;
import dto.RequestAnswerDTO;
import dto.RequestPlayerInformationDTO;
import dto.ResponsePlayerDTO;
import dto.ResponseWinningResultDTO;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private static BlackJackRule blackJackRule = new BlackJackRule();

    private BlackjackController() {
    }

    public static void run() {
        CardDeck cardDeck = new CardDeck();

        List<RequestPlayerInformationDTO> requestPlayerInformationDTOS = InputView.inputPlayerInformation();
        Map<String, Integer> playerInformations = new LinkedHashMap<>();
        for (RequestPlayerInformationDTO requestPlayerInformationDTO : requestPlayerInformationDTOS) {
            playerInformations.put(requestPlayerInformationDTO.getPlayerName(), requestPlayerInformationDTO.getMoney());
        }
        Users users = PlayerFactory.create(cardDeck, playerInformations);
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(users);
        OutputView.printInitialResult(responsePlayerDTOS);

        runUserBlackJack(cardDeck, users.getPlayer());
        runDealerBlackJack(cardDeck, users.getDealer());

        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(users));

        WinningResult winningResult = new WinningResult(users);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printFinalProfit(responseWinningResultDTO);
    }

    private static void runUserBlackJack(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            runBlackJackPerUser(cardDeck, player);
        }
    }

    private static void runBlackJackPerUser(CardDeck cardDeck, Player player) {
        if (player.isBlackJack()) {
            return;
        }

        while (blackJackRule.isHit(player) && getAnswer(player).isYes()) {
            blackJackRule.hit(player, cardDeck.drawCard());
            OutputView.printUserCard(ResponsePlayerDTO.create(player));
        }
    }

    private static void runDealerBlackJack(CardDeck cardDeck, Dealer dealer) {
        Cards dealerCards = dealer.getCard();
        if (dealerCards.isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealer, cardDeck.drawCard());
            OutputView.printDealerAdditionalCard();
        }
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
