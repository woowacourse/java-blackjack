package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.scoreboard.DealerGameResult;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGameController {

    public void start() {
        BlackjackGame blackjackGame = startGameAndFirstDraw();
        printFirstDrawInformation(blackjackGame);

        processUserRound(blackjackGame);
        blackjackGame.processDealerRound();
        OutputView.printDealerMoreDrawMessage();

        DealerGameResult dealerGameResult = blackjackGame.createDealerGameResult();
        UserGameResult userGameResult = blackjackGame.createUserGameResult();

        printParticipantsCardsAndScore(dealerGameResult, userGameResult);
        printFinalProfit(dealerGameResult, userGameResult);
    }

    private static BlackjackGame startGameAndFirstDraw() {
        try {
            Users users = Users.from(askUserNames());
            askUserMoney(users);
            return BlackjackGame.createAndFirstDraw(users);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return startGameAndFirstDraw();
        }
    }

    private static void askUserMoney(Users users) {
        try {
            users.toList().forEach(user -> user.batMoney(InputView.askPlayersMoney(user)));
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            askUserMoney(users);
        }
    }

    private static List<Name> askUserNames() {
        return InputView.askPlayersName().stream()
                .map(Name::from)
                .collect(Collectors.toList());
    }

    private static void printFirstDrawInformation(BlackjackGame blackjackGame) {
        OutputView.printDrawMessage(blackjackGame.getUserNames());
        OutputView.println();

        printFirstDrawCards(blackjackGame);
        OutputView.println();
    }

    private static void printFirstDrawCards(BlackjackGame blackjackGame) {
        OutputView.printDealerFirstCard(blackjackGame.getDealerFirstCard());
        blackjackGame.getUsers().forEach(OutputView::printCardList);
    }

    private static void processUserRound(BlackjackGame blackjackGame) {
        List<User> users = blackjackGame.getUsers();
        for (User user : users) { // 각 유저별로 확인
            decideHitOrStay(blackjackGame, user);
        }
        OutputView.println();
    }

    private static void decideHitOrStay(BlackjackGame blackjackGame, User user) {
        while (user.canContinueGame()) {  // 유저가 카드를 더 받을 수 있는 상태라면 물어봄
            askHitOrStay(user, blackjackGame);
        }
    }

    private static void askHitOrStay(User user, BlackjackGame blackjackGame) {
        try {
            boolean hitOrStay = InputView.askMoreDraw(user.getName());
            userDrawOrStop(user, hitOrStay, blackjackGame);
            printUserCurrentCards(user);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askHitOrStay(user, blackjackGame);
        }
    }

    private static void userDrawOrStop(User user, boolean hitOrStay, BlackjackGame blackjackGame) {
        if (hitOrStay) {
            blackjackGame.drawCardToUser(user);
            return;
        }
        user.stayUser();
    }

    private static void printUserCurrentCards(User currentUser) {
        OutputView.printCardList(currentUser);
    }

    private void printParticipantsCardsAndScore(DealerGameResult dealerGameResult, UserGameResult userGameResult) {
        OutputView.printCardListAndScore(dealerGameResult.getDealer());


        userGameResult.getUserSet().forEach(OutputView::printCardListAndScore);
        OutputView.println();
    }

    private void printFinalProfit(DealerGameResult dealerGameResult, UserGameResult userGameResult) {
        OutputView.printFinalDealerProfit(dealerGameResult, userGameResult);
        OutputView.printFinalUserProfit(userGameResult);
    }
}
