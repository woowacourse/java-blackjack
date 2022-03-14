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

    private static final String PLAYER_RESULT_FORMAT = "%s: %s\n";
    private static final String FINAL_RESULT_MESSAGE = "\n###최종 승패";
    private static final String DEALER = "딜러:";
    private static final String WIN_RESULT = " %d승";
    private static final String DRAW_RESULT = " %d무";
    private static final String LOSE_RESULT = " %d패";

    public static void printInitStatus(Dealer dealer, List<Player> players) {
        List<String> names = new ArrayList<>(List.of("딜러"));
        players.stream()
                .map(player -> player.getName())
                .map(names::add);
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장의 카드를 나누었습니다.");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeStatusFormat("딜러", dealer.getMyCards()))
                .append("\n");
        players.forEach(player -> stringBuilder.append(makeStatusFormat(player.getName(),player.getMyCards()))
                .append( "\n"));

        System.out.println(stringBuilder.toString());
    }

    private static String makeStatusFormat(String name, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
        return name + ": " + String.join(", ", cardNames);
    }

    public static void printCards(Player person) {
        System.out.println(makeStatusFormat(person.getName(), person.getMyCards()));
    }

    public static void printDealerAdditionalCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsAndScores(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(makeStatusFormat("딜러", dealer.getMyCards()) + printScoreResult(dealer.score()));

        for (Player player: players) {
            System.out.println(makeStatusFormat(player.getName(), player.getMyCards())
                    + printScoreResult(player.score()));
        }
    }

    private static String printScoreResult(int score) {
        return (" - 결과: " + score);
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
