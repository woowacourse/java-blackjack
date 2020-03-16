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

        Dealer dealer = new Dealer(cards.giveCard(),cards.giveCard());
        Players players = new Players(cards, playersName.getPlayerName());

        UsersInformations playersInformations = new UsersInformations(players);
        UserInformation dealerInformation = new UserInformation(dealer);
        OutputView.printUserCard(dealerInformation.getName(), dealerInformation.getCardInformation());

        List<UserInformation> playersInformation = playersInformations.getPlayerInformation();
        playersInformation.forEach(playerInformation ->
                OutputView.printUserCard(playerInformation.getName(), playerInformation.getCardInformation())
        );

        for (Player player : players.getPlayers()) {
            userService(cards, player);
        }
        DealerService(cards, dealer);
        result(players,dealer);
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

    private static void result(Players players,Dealer dealer) {
        UserInformation dealerInformation = new UserInformation(dealer);
        UsersInformations playerInformations = new UsersInformations(players);

        OutputView.printFinalResult(
                dealerInformation.getName(),dealerInformation.getCardInformation(),dealerInformation.getScore()
        );
        playerInformations.getPlayerInformation()
                .forEach(userInformation -> OutputView.printFinalResult(
                        userInformation.getName(), userInformation.getCardInformation(), userInformation.getScore())
                );

        WinningResult winningResult = new WinningResult(players,dealer);
        OutputView.printWinningResult(winningResult.generateWinningUserResult(players));
    }

    private static AnswerType getAnswer(Player player) {
        UserInformation userInformation = new UserInformation(player);
        Answer answer = new Answer(InputView.inputAnswer(userInformation.getName()));

        return AnswerType.AnswerValueOf(answer.getAnswer());
    }
}
