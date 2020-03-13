package controller;

import domain.AnswerType;
import domain.PlayerFactory;
import domain.WinningResult;
import domain.card.Cards;
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
    private static final int BLACK_JACK = 21;

    private BlackjackController() {
    }

    public static void run() {
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        Cards cards = new Cards();

        RequestPlayerNamesDTO requestPlayerNamesDTO = inputRequestPlayerNamesDTO();
        Players players = playerFactory.createPlayers(cards, requestPlayerNamesDTO.getPlayerName());
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(players);
        OutputView.printInitialResult(responsePlayerDTOS);

        pickCard(cards, players);
        pickCardDealer(cards, players);

        OutputView.printFinalResult(ResponsePlayerDTO.createResponsePlayerDTOs(players));

        WinningResult winningResult = new WinningResult(players);
        ResponseWinningResultDTO responseWinningResultDTO = ResponseWinningResultDTO.create(
                winningResult.getWinningResult());
        OutputView.printWinningResult(responseWinningResultDTO);
    }

    private static RequestPlayerNamesDTO inputRequestPlayerNamesDTO() {
        try {
            return InputView.inputPlayerName();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputRequestPlayerNamesDTO();
        }
    }

    private static void pickCard(Cards cards, Players players) {
        for (User user : players.getUsers()) {
            choosePickCard(cards, user);
        }
    }

    private static void choosePickCard(Cards cards, User user) {
        AnswerType answerType = getAnswerType(user);

        if (answerType.equals(AnswerType.NO)) {
            return;
        }

        do {
            user.insertCard(cards, answerType);

            OutputView.printUserCard(ResponsePlayerDTO.create(user));
            answerType = validateBlackJack(user, answerType);
        } while (AnswerType.YES.equals(answerType) && isBlackJack(user));
    }

    private static AnswerType validateBlackJack(Player user, AnswerType answerType) {
        if (isBlackJack(user)) {
            answerType = getAnswerType(user);
        }
        return answerType;
    }

    private static boolean isBlackJack(Player user) {
        return user.sumCardNumber() < BLACK_JACK;
    }

    private static AnswerType getAnswerType(Player user) {
        try {
            RequestAnswerDTO requestAnswerDTO = InputView.inputAnswer(ResponsePlayerDTO.create(user));
            return AnswerType.findAnswerType(requestAnswerDTO.getAnswer());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getAnswerType(user);
        }
    }

    private static void pickCardDealer(Cards cards, Players players) {
        if (players.getDealer().isAdditionalCard(cards)) {
            OutputView.printDealerAdditionalCard();
        }
    }
}
