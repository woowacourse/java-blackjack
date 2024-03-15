package view;

import domain.card.Card;
import domain.money.PlayersMoney;
import domain.player.Hand;
import domain.player.Player;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import view.viewer.HandViewer;
import view.viewer.card.CardViewer;

public class OutputView {
    private static final String DEALER_NAME_VALUE = "딜러";

    private OutputView() {
    }

    public static void printStartStatus(Hand dealerHand, Set<Player> players) {
        printUserNames(players);
        printUsersStartCards(dealerHand, players);
        System.out.println();
    }

    private static void printUserNames(Set<Player> players) {
        String names = players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME_VALUE, names);
    }

    private static void printUsersStartCards(Hand dealerHand, Set<Player> players) {
        System.out.println(DEALER_NAME_VALUE + "카드: " + CardViewer.show(dealerHand.getFirstCard()));
        players.forEach(player -> printUserAndCards(player.getNameValue(), player.getAllCards()));
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

    public static void printAllUserCardsAndSum(Hand dealerHand, Set<Player> players) {
        System.out.println();
        System.out.println(showUserNameAndCards(
                DEALER_NAME_VALUE, dealerHand.getCards()) + " - 결과: " + dealerHand.sumCard());
        players.forEach(player -> System.out.println(showUserNameAndCards(
                player.getNameValue(), player.getAllCards()) + " - 결과: " + player.sumHand()));
    }

    public static void printFinalResult(PlayersMoney playersMoney) {
        System.out.println("\n## 최종 수익");
        System.out.println(DEALER_NAME_VALUE + ": " + playersMoney.calculateDealerMoney());
        playersMoney.forEach(((player, money) ->
                System.out.println(player.getNameValue() + ": " + money.value())));
    }
}
