package ui.output;

import model.Result;
import model.User;
import model.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printDivideInitialCards(final Result result) {
        final Map<User, List<Card>> scoreBoards = result.getScoreBoards();

        printDividedMessage(scoreBoards);
        printPlayersHands(scoreBoards);
        System.out.print(System.lineSeparator());
    }

    private static void printDividedMessage(final Map<User, List<Card>> scoreBoards) {
        final String playerNames = scoreBoards.keySet().stream()
                .map(User::getName)
                .filter(name -> !"딜러".equals(name))
                .collect(Collectors.joining(", "));
        System.out.printf(System.lineSeparator() + (DIVIDE_CARDS_MESSAGE) + "%n", playerNames);
    }

    private static void printPlayersHands(final Map<User, List<Card>> scoreBoards) {
        scoreBoards.keySet()
                .forEach(user -> System.out.println(user.getName() + ": " + createCardInfo(scoreBoards, user)));
    }

    public static void printPlayerCardsInfo(final User user, final Result result) {
        System.out.println(user.getName() + ": " + createCardInfo(result.getScoreBoards(), user));
    }

    private static String createCardInfo(final Map<User, List<Card>> scoreBoards, final User user) {
        StringBuilder stringBuilder = new StringBuilder();
        scoreBoards.get(user).forEach(card -> stringBuilder.append(card.toString()).append(", "));
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    public static void printDealerGetCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD);
    }
}
