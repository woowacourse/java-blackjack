package ui.output;

import model.Player;
import model.Result;
import model.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";

    public static void printDivideInitialCards(final Result result) {
        final Map<Player, List<Card>> scoreBoards = result.getScoreBoards();

        printDividedMessage(scoreBoards);
        printPlayersHands(scoreBoards);
        System.out.print(System.lineSeparator());
    }

    private static void printDividedMessage(final Map<Player, List<Card>> scoreBoards) {
        final String playerNames = scoreBoards.keySet().stream()
                .map(Player::getName)
                .filter(name -> !name.equals("딜러"))
                .collect(Collectors.joining(", "));
        System.out.printf(System.lineSeparator() + (DIVIDE_CARDS_MESSAGE) + "%n", playerNames);
    }

    private static void printPlayersHands(final Map<Player, List<Card>> scoreBoards) {
        scoreBoards.keySet()
                .forEach(player -> System.out.println(player.getName() + ": " + createCardInfo(scoreBoards, player)));
    }

    private static String createCardInfo(final Map<Player, List<Card>> scoreBoards, final Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        scoreBoards.get(player).forEach(card -> stringBuilder.append(card.toString()).append(", "));
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
