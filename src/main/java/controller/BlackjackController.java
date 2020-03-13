package controller;

import domain.AnswerType;
import domain.CardCalculator;
import domain.PlayerFactory;
import domain.WinningResult;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;
import dto.RequestAnswerDTO;
import dto.RequestPlayerNameDTO;
import dto.ResponsePlayerDTO;
import dto.ResponseWinningResultDTO;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public static void run() {
        Cards cards = new Cards();
        RequestPlayerNameDTO requestPlayerNameDTO = InputView.inputPlayerName();
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        Players players = playerFactory.createPlayers(cards, requestPlayerNameDTO.getPlayerName());

        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.of(players);
        OutputView.printInitialResult(responsePlayerDTOS);

        for (User user : players.getUsers()) {
            userService(cards, user);
        }
        DealerService(cards, players.getDealer());
        result(players);
    }

    private static void userService(Cards cards, User user) {
        AnswerType answerType = getAnswer(user);

        if (answerType.isEqualsAnswer(AnswerType.NO)) {
            return;
        }

        while (answerType.isEqualsAnswer(AnswerType.YES) && CardCalculator.isUnderBlackJack(user.getCard())) {
            user.insertCard(cards);
            OutputView.printUserCard(ResponsePlayerDTO.of(user));

            if (CardCalculator.isUnderBlackJack(user.getCard())) {
                answerType = getAnswer(user);
                continue;
            }
            OutputView.printUserCardsOverBlackJack(ResponsePlayerDTO.of(user));
        }
    }

    private static void DealerService(Cards cards, Dealer dealer) {
        if (dealer.isAdditionalCard(cards)) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private static void result(Players players) {
        OutputView.printFinalResult(ResponsePlayerDTO.of(players));

        WinningResult winningResult = new WinningResult(players);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.of(
                winningResult.getWinningResult());
        OutputView.printWinningResult(responseWinningResultDTO);
    }

    private static AnswerType getAnswer(User user) {
        RequestAnswerDTO requestAnswerDTO = InputView.inputAnswer(ResponsePlayerDTO.of(user));
        return AnswerType.findAnswerType(requestAnswerDTO.getAnswer());
    }
}
