package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String NAME_CARD_DELIMITER = ": ";
    private static final String SCORE_MESSAGE = "결과: %d";
    private static final String INIT_CARD_DISTRIBUTION_MESSAGE = "%s에게 %d장을 나눠주었습니다.\n";
    private static final String ADDICTIONAL_CARD_DEALDER_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final int INIT_CARD_NUM = 2;

    private static final String PLAYER_RESULT_FORMAT = "%s: %s\n";
    private static final String FINAL_RESULT_MESSAGE = "\n###최종 승패";
    private static final String DEALER = "딜러:";
    private static final String WIN_RESULT = " %d승";
    private static final String DRAW_RESULT = " %d무";
    private static final String LOSE_RESULT = " %d패";

    public static void printInitStatus(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        String prefixString = dealer.getName() + String.join(NAME_DELIMITER, playerNames);
        System.out.printf(INIT_CARD_DISTRIBUTION_MESSAGE, prefixString, INIT_CARD_NUM);

        printCards(dealer, true);
        for (Player player : players) {
            printCards(player, true);
        }
    }

    private static String makeStatusFormat(String name, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
        return name + NAME_CARD_DELIMITER + String.join(NAME_DELIMITER, cardNames);
    }

    public static void printCards(Player person, boolean newLine) {
        System.out.print(makeStatusFormat(person.getName(), person.getMyCards()));
        if (newLine) {
            System.out.println();
        }
    }

    public static void printDealerAdditionalCard() {
        System.out.println(ADDICTIONAL_CARD_DEALDER_MESSAGE);
    }

    public static void printCardsWithScore(Dealer dealer, List<Player> players) {
        printCards(dealer, false);
        printScore(dealer);
        for (Player player: players) {
            printCards(player, false);
            printScore(player);
        }
    }

    private static void printScore(Player player) {
        System.out.printf(SCORE_MESSAGE, player.score());
    }

    public static void printResults(Map<String, Result> results) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerResult(new ArrayList<>(results.values()));
        for (Map.Entry<String, Result> entry : results.entrySet()) {
            System.out.printf(PLAYER_RESULT_FORMAT, entry.getKey(), entry.getValue().getName());
        }
    }

    private static void printDealerResult(List<Result> results) {
        int winCount = countDealerSpecificResult(Result.LOSE, results);
        int drawCount = countDealerSpecificResult(Result.DRAW, results);
        int loseCount = countDealerSpecificResult(Result.WIN, results);
        System.out.printf(DEALER);
        if (winCount > 0) {
            System.out.printf(WIN_RESULT, winCount);
        }
        if (drawCount > 0) {
            System.out.printf(DRAW_RESULT, drawCount);
        }
        if (loseCount > 0) {
            System.out.printf(LOSE_RESULT, loseCount);
        }
        System.out.println();
    }

    private static int countDealerSpecificResult(Result specificResult, List<Result> results) {
        return (int) results.stream()
                .filter(result -> result.equals(specificResult))
                .count();
    }
}
