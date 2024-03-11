package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.List;
import java.util.stream.Collectors;
import view.card.CardViewer;

public class UserView {
    public static final String DEALER_NAME_VALUE = "딜러";

    public void printStartStatus(Users users) {
        printUserNames(users);
        printAllUserVisibleCards(users);
        System.out.println();
    }

    private void printUserNames(Users users) {
        String names = users.getPlayers()
                .stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME_VALUE, names);
    }

    private void printAllUserVisibleCards(Users users) {
        Dealer dealer = users.getDealer();
        System.out.println(joinUserNameAndCards(DEALER_NAME_VALUE, dealer.getVisibleCards()));
        users.getPlayers()
                .forEach(this::printPlayerAndVisibleCards);
    }

    public void printBust(User player) {
        printPlayerAndVisibleCards(player);
        System.out.println("버스트!");
    }

    public void printDealerHitMessage() {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", DEALER_NAME_VALUE);
    }

    public void printPlayerAndVisibleCards(User player) {
        System.out.println(joinUserNameAndCards(player.getNameValue(), player.getVisibleCards()));
    }

    public void printAllUserCardsAndSum(Users users) {
        System.out.println();
        Dealer dealer = users.getDealer();
        System.out.println(joinUserNameAndCards(DEALER_NAME_VALUE, dealer.getAllCards())
                + "- 결과: " + dealer.sumUserDeck());
        users.getPlayers()
                .forEach(player ->
                        System.out.println(joinUserNameAndCards(player.getNameValue(), player.getAllCards())
                                + " - 결과: " + player.sumUserDeck()));
    }

    private String joinUserNameAndCards(String name, List<Card> cards) {
        return name + "카드: " + joinCards(cards);
    }

    private String joinCards(List<Card> cards) {
        return cards.stream()
                .map(CardViewer::show)
                .collect(Collectors.joining(", "));
    }
}
