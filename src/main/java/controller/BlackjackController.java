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
import dto.RequestPlayerNamesDTO;
import dto.ResponsePlayerDTO;
import dto.ResponseWinningResultDTO;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {
    private static BlackJackRule blackJackRule = new BlackJackRule();

    private BlackjackController() {
    }

    public static void run() {
        CardDeck cardDeck = new CardDeck();

        RequestPlayerNamesDTO requestPlayerNamesDTO = inputRequestPlayerNamesDTO();
        Users users = PlayerFactory.create(cardDeck, requestPlayerNamesDTO.getPlayerName());
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(users);
        OutputView.printInitialResult(responsePlayerDTOS);

        runUserBlackJack(cardDeck, users.getPlayer());
        runDealerBlackJack(cardDeck, users.getDealer());

        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(users));

        WinningResult winningResult = new WinningResult(users);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printWinningResult(responseWinningResultDTO);
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

    private static RequestPlayerNamesDTO inputRequestPlayerNamesDTO() {
        try {
            return InputView.inputPlayerName();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputRequestPlayerNamesDTO();
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
