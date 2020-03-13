package controller;

import domain.AnswerType;
import domain.PlayerFactory;
import domain.WinningResult;
import domain.card.Cards;
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

    public static final int BLACK_JACK = 21;

    public static void run() {
        RequestPlayerNamesDTO requestPlayerNameDTO = InputView.inputPlayerName();
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        Cards cards = new Cards();
        Players players = playerFactory.createPlayers(cards, requestPlayerNameDTO.getPlayerName());
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(players);
        OutputView.printInitialResult(responsePlayerDTOS);

        for (User user : players.getUsers()) {
            AnswerType answerType = getAnswerType(user);

            if (answerType.equals(AnswerType.NO)) {
                continue;
            }
            do {
                user.insertCard(cards, answerType);

                OutputView.printUserCard(ResponsePlayerDTO.create(user));
                answerType = validateBlackJack(user, answerType);
            } while (answerType.equals(AnswerType.YES) && isBlackJack(user));
        }

        if (players.getDealer().isAdditionalCard(cards)) {
            OutputView.printDealerAdditionalCard();
        }

        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(players));
        WinningResult winningResult = new WinningResult(players);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printWinningResult(responseWinningResultDTO.getWinningResult());
    }

    private static AnswerType validateBlackJack(User user, AnswerType answerType) {
        if (isBlackJack(user)) {
            answerType = getAnswerType(user);
        }
        return answerType;
    }

    private static boolean isBlackJack(User user) {
        return user.sumCardNumber() < BLACK_JACK;
    }

    private static AnswerType getAnswerType(User user) {
        RequestAnswerDTO requestAnswerDTO = InputView.inputAnswer(ResponsePlayerDTO.create(user));
        return AnswerType.findAnswerType(requestAnswerDTO.getAnswer());
    }
}
