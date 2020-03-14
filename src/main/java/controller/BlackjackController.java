package controller;

import domain.AnswerType;
import domain.card.CardCalculator;
import domain.player.UserFactory;
import domain.WinningResult;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Users;
import domain.player.Player;
import dto.Answer;
import dto.PlayerName;
import dto.ResponsePlayerDTO;
import dto.WinningPlayerResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public static void run() {
        Cards cards = new Cards();
        PlayerName playerName = InputView.inputPlayerName();
        Users users = UserFactory.createPlayers(cards, playerName.getPlayerName());

        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.of(users);
        OutputView.printInitialResult(responsePlayerDTOS);

        for (Player player : users.getUsers()) {
            userService(cards, player);
        }
        DealerService(cards, users.getDealer());
        result(users);
    }

    private static void userService(Cards cards, Player player) {
        AnswerType answerType = getAnswer(player);

        if (answerType.isEqualsAnswer(AnswerType.NO)) {
            return;
        }

        while (answerType.isEqualsAnswer(AnswerType.YES) && CardCalculator.isUnderBlackJack(player.getCard())) {
            player.insertCard(cards);
            OutputView.printUserCard(ResponsePlayerDTO.of(player));

            if (CardCalculator.isUnderBlackJack(player.getCard())) {
                answerType = getAnswer(player);
                continue;
            }
            OutputView.printUserCardsOverBlackJack(ResponsePlayerDTO.of(player));
        }
    }

    private static void DealerService(Cards cards, Dealer dealer) {
        if (dealer.isAdditionalCard(cards)) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private static void result(Users users) {
        OutputView.printFinalResult(ResponsePlayerDTO.of(users));

        WinningResult winningResult = new WinningResult(users);
        WinningPlayerResult winningPlayerResult = WinningPlayerResult.of(
                winningResult.getWinningResult());
        OutputView.printWinningResult(winningPlayerResult);
    }

    private static AnswerType getAnswer(Player player) {
        Answer answer = InputView.inputAnswer(ResponsePlayerDTO.of(player));
        return AnswerType.findAnswerType(answer.getAnswer());
    }
}
