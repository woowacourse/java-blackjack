package ui.output;

import model.Dealer;
import model.User;
import model.Users;

import java.util.stream.Collectors;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printDivideInitialCards(final Users users) {
        printReceiveMessage(users);
        printPlayersHand(users);
        System.out.print(System.lineSeparator());
    }

    private static void printReceiveMessage(final Users users) {
        final String playerNames = users.getUsers().stream()
                .filter(user -> !Dealer.getInstance().equals(user))
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.printf(System.lineSeparator() + (DIVIDE_CARDS_MESSAGE) + "%n", playerNames);
    }

    private static void printPlayersHand(final Users users) {
        users.getUsers().forEach(OutputView::printNameAndHandInfo);
    }

    public static void printPlayerHand(final User user) {
        printNameAndHandInfo(user);
    }

    private static void printNameAndHandInfo(final User user) {
        System.out.println(nameAndHandInfo(user));
    }

    public static void printDealerGetCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD + System.lineSeparator());
    }

    public static void printTotalValue(final Users users) {
        users.getUsers().forEach(
                user -> System.out.println(createTotalValueMessage(user))
        );
    }

    private static String nameAndHandInfo(final User user) {
        return String.format("%s: %s", user.getName(), user.getHandInfo());
    }

    private static String createTotalValueMessage(final User user) {
        return nameAndHandInfo(user) + String.format(" - 결과: %s", user.calculateTotalValue());
    }
}
