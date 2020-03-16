package controller;

import domain.Answer;
import domain.BlackJackRule;
import domain.PlayerFactory;
import domain.WinningResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;
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
        Players players = PlayerFactory.create(cardDeck, requestPlayerNamesDTO.getPlayerName());
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(players);
        OutputView.printInitialResult(responsePlayerDTOS);

        runUserBlackJack(cardDeck, players.getUsers());
        runDealerBlackJack(cardDeck, players.getDealer());

        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(players));

        WinningResult winningResult = new WinningResult(players);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printWinningResult(responseWinningResultDTO);
    }

    private static void runUserBlackJack(CardDeck cardDeck, List<User> users) {
        for (User user : users) {
            runBlackJackPerUser(cardDeck, user);
        }
    }

    private static void runBlackJackPerUser(CardDeck cardDeck, User user) {
        if (user.isBlackJack()) {
            return;
        }

        Answer answer = getAnswer(user);
        while (blackJackRule.isHit(user, answer)) {
            blackJackRule.hit(user, cardDeck.hit());
            OutputView.printUserCard(ResponsePlayerDTO.create(user));
            if (blackJackRule.isUserCardSumOverBlackJack(user)) {
                break;
            }
            answer = getAnswer(user);
        }
    }

    private static void runDealerBlackJack(CardDeck cardDeck, Dealer dealer) {
        if (dealer.isAdditionalCard()) {
            blackJackRule.hit(dealer, cardDeck.hit());
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

    private static Answer getAnswer(Player user) {
        try {
            RequestAnswerDTO requestAnswerDTO = InputView.inputAnswer(ResponsePlayerDTO.create(user));
            return Answer.valueOf(requestAnswerDTO.getAnswer());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getAnswer(user);
        }
    }
}
