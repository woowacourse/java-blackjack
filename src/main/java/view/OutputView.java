package view;

import domain.card.Card;
import domain.game.PlayersMoney;
import domain.user.GameUsers;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;
import view.viewer.HandViewer;

public class OutputView {
    private static final String DEALER_NAME_VALUE = "딜러";

    private OutputView() {
    }

    public static void printStartStatus(GameUsers gameUsers) {
        printUserNames(gameUsers);
        printUsersStartCards(gameUsers);
        System.out.println();
    }

    private static void printUserNames(GameUsers gameUsers) {
        String names = gameUsers.getPlayers()
                .stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME_VALUE, names);
    }

    private static void printUsersStartCards(GameUsers gameUsers) {
        printUserAndCards(DEALER_NAME_VALUE, gameUsers.getDealer().getVisibleCard());
        gameUsers.getPlayers()
                .forEach(player -> printUserAndCards(player.getNameValue(), player.getAllCards()));
    }

    public static void printUserAndCards(String name, List<Card> cards) {
        System.out.println(showUserNameAndCards(name, cards));
    }

    private static String showUserNameAndCards(String name, List<Card> cards) {
        return name + "카드: " + HandViewer.show(cards);
    }

    public static void printBlackjack(String nameValue) {
        System.out.println("\n" + nameValue + " 블랙잭!");
    }

    public static void printBust() {
        System.out.println("버스트!");
    }

    public static void printDealerHitMessage() {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", DEALER_NAME_VALUE);
    }

    public static void printAllUserCardsAndSum(GameUsers gameUsers) {
        System.out.println();
        System.out.println(showUserNameAndCards(DEALER_NAME_VALUE, gameUsers.getDealer().getAllCards())
                + " - 결과: " + gameUsers.getDealer().sumHand());
        gameUsers.getPlayers()
                .forEach(player ->
                        System.out.println(showUserNameAndCards(player.getNameValue(), player.getAllCards())
                                + " - 결과: " + player.sumHand()));
    }

    public static void printFinalResult(PlayersMoney playersMoney) {
        System.out.println("\n## 최종 수익");
        System.out.println(DEALER_NAME_VALUE + ": " + playersMoney.calculateDealerMoney());
        playersMoney.getPlayersMoney()
                .forEach(((player, betAmount) ->
                        System.out.println(player.getNameValue() + ": " + betAmount.value())));
    }
}
