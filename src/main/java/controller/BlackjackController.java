package controller;

import domain.*;
import domain.card.CardCalculator;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Users;
import domain.player.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public static void run() {
        Cards cards = new Cards();
        PlayersName playersName = new PlayersName(InputView.inputPlayerName());
        Users users = new Users(cards, playersName.getPlayerName());
        UsersInformation usersInformation = new UsersInformation(users);

        OutputView.printInitialResult(playersName.getPlayerName());
        UserInformation dealer = usersInformation.getDealerInformation();
        OutputView.printDealerCard(dealer.getName(),dealer.getCardInformation());

        for (Player player : users.getPlayers()) {
            userService(cards, player);
        }
        DealerService(cards, users.getDealer());
        result(users,usersInformation);
    }

    private static void userService(Cards cards, Player player) {
        AnswerType answerType = getAnswer(player);

        if (answerType.isEqualsAnswer(AnswerType.NO)) {
            return;
        }

        while (answerType.isEqualsAnswer(AnswerType.YES) && CardCalculator.isUnderBlackJack(player.getCard())) {
            player.drawCard(cards.giveCard());
            UserInformation usersInformation = new UserInformation(player);
            OutputView.printUserCard(usersInformation.getName(),usersInformation.getCardInformation());

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

    private static void result(Users users, UsersInformation usersInformation) {
        OutputView.printFinalResult(usersInformation.getUsersInformation());

        WinningResult winningResult = new WinningResult(users);
        OutputView.printWinningResult(winningResult.generateWinningUserResult());
    }

    private static AnswerType getAnswer(Player player) {
        Answer answer = new Answer(InputView.inputAnswer(new UserInformation(player)));
        return AnswerType.findAnswerValueOf(answer.getAnswer());
    }
}
