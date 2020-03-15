package controller;

import domain.*;
import domain.answer.Answer;
import domain.answer.AnswerType;
import domain.card.CardCalculator;
import domain.card.Cards;
import domain.player.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public static void run() {
        Cards cards = new Cards();
        PlayersName playersName = new PlayersName(InputView.inputPlayerName());
        OutputView.printInitialResult(playersName.getPlayerName());

        Users users = new Users(cards, playersName.getPlayerName());
        UsersInformation usersInformation = new UsersInformation(users);
        UserInformation dealerInformation = usersInformation.getDealerInformation();
        OutputView.printUserCard(dealerInformation.getName(), dealerInformation.getCardInformation());

        List<UserInformation> playersInformation = usersInformation.getPlayerInformation();
        playersInformation.forEach(playerInformation ->
                OutputView.printUserCard(playerInformation.getName(), playerInformation.getCardInformation())
        );

        for (Player player : users.getPlayers()) {
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
            player.drawCard(cards.giveCard());
            UserInformation usersInformation = new UserInformation(player);
            OutputView.printUserCard(usersInformation.getName(), usersInformation.getCardInformation());

            if (CardCalculator.isUnderBlackJack(player.getCard())) {
                answerType = getAnswer(player);
                continue;
            }
            OutputView.printUserCardsOverBlackJack(usersInformation.getName());
        }
    }

    private static void DealerService(Cards cards, Dealer dealer) {
        if (dealer.isAdditionalCard(cards.giveCard())) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private static void result(Users users) {
        UsersInformation usersInformation = new UsersInformation(users);

        usersInformation.getUsersInformation()
                .forEach(userInformation -> OutputView.printFinalResult(
                        userInformation.getName(), userInformation.getCardInformation(), userInformation.getScore())
                );

        WinningResult winningResult = new WinningResult(users);
        OutputView.printWinningResult(winningResult.generateWinningUserResult(users));
    }

    private static AnswerType getAnswer(Player player) {
        UserInformation userInformation = new UserInformation(player);
        Answer answer = new Answer(InputView.inputAnswer(userInformation.getName()));

        return AnswerType.AnswerValueOf(answer.getAnswer());
    }
}
