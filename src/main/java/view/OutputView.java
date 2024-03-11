package view;

import static view.viewer.UserViewer.DEALER_NAME_VALUE;

import domain.game.Game;
import domain.game.PlayerResults;
import domain.game.Result;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.Map;
import java.util.stream.Collectors;
import view.viewer.ResultViewer;
import view.viewer.UserViewer;

public class OutputView {

    public static void printStartStatus(Users users) {
        printUserNames(users);
        printAllUserVisibleCards(users);
        System.out.println();
    }

    private static void printUserNames(Users users) {
        String names = users.getPlayers()
                .stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME_VALUE, names);
    }

    private static void printAllUserVisibleCards(Users users) {
        users.getUsers()
                .forEach(OutputView::printPlayerAndVisibleCards);
    }

    public static void printBust(User player) {
        printPlayerAndVisibleCards(player);
        System.out.println("버스트!");
    }

    public static void printDealerHitMessage() {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", DEALER_NAME_VALUE);
    }

    public static void printPlayerAndVisibleCards(User user) {
        System.out.println(UserViewer.showVisibleCards(user));
    }

    public static void printAllUserCardsAndSum(Users users) {
        System.out.println();
        users.getUsers()
                .forEach(user ->
                        System.out.println(UserViewer.showAllCards(user)
                                + " - 결과: " + user.sumUserDeck()));
    }

    public static void printFinalResult(Game game) {
        System.out.println("\n## 최종 승패");
        PlayerResults playerResults = game.generatePlayerResults();
        Map<Result, Integer> dealerResult = playerResults.generateDealerResult();
        System.out.println(DEALER_NAME_VALUE + ": " + ResultViewer.showDealerResult(dealerResult));

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getNameValue() + ": " + ResultViewer.show(result))));
    }
}
