package controller;

import domain.Answer;
import domain.BlackJackRule;
import domain.UserFactory;
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

        Map<String, Integer> playerInformation = getPlayerInformation();
        Users users = UserFactory.create(cardDeck, playerInformation);
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

    private static Map<String, Integer> getPlayerInformation() {
        Map<String, Integer> playerInformation = new LinkedHashMap<>();
        List<RequestPlayerInformationDTO> requestPlayerInformationDTOS = InputView.inputPlayerInformation();
        for (RequestPlayerInformationDTO requestPlayerInformationDTO : requestPlayerInformationDTOS) {
            playerInformation.put(requestPlayerInformationDTO.getPlayerName(), requestPlayerInformationDTO.getMoney());
        }
        return playerInformation;
    }

    private static void runPlayerBlackJack(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            runBlackJackPerPlayer(cardDeck, player);
        }
    }

    private static void runBlackJackPerPlayer(CardDeck cardDeck, Player player) {
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
