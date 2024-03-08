package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Result;
import blackjack.domain.ResultStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialHand(Dealer dealer, List<Player> players) {
        System.out.println();
        print(dealOut(dealer, players));

        print(singleCard(dealer));
        for (Player player : players) {
            printTotalHand(player);
        }
        System.out.println();
    }

    public static void printTotalHand(Player player) {
        print(totalHand(player));
    }

    private static String dealOut(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        return String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), playerNames);
    }

    private static String singleCard(Dealer dealer) {
        List<Card> cards = dealer.getHand().getCards();
        return String.format("%s: %s", dealer.getName(), extractCardName(cards.get(0)));
    }

    private static String totalHand(Player player) {
        List<Card> cards = player.getHand().getCards();
        String hand = cards.stream()
                .map(OutputView::extractCardName)
                .collect(Collectors.joining(", "));

        return String.format("%s: %s", player.getName(), hand);
    }

    public static void printHandWithScore(Dealer dealer, List<Player> players) {
        print(totalHand(dealer) + resultScore(dealer));
        for (Player player : players) {
            print(totalHand(player) + resultScore(player));
        }
        System.out.println();
    }

    private static String resultScore(Player player) {
        int score = player.calculate();
        return String.format(" - 결과: %d", score);
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String extractCardName(Card card) {
        return card.getNumber().getName() + card.getSymbol().getName();
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.println(String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName()));
        System.out.println();
    }

    public static void printDealerStand(Dealer dealer) {
        System.out.println(String.format("%s는 17이상이라 카드를 더 받지 않습니다.", dealer.getName()));
        System.out.println();
    }

    public static void printResult(Result result, Dealer dealer) {
        Map<Player, ResultStatus> results = result.getResults();
        Map<ResultStatus, Long> resultStatusLongMap = result.calculateDealerResult();

        System.out.println("## 최종 승패");
        printDealerResult(dealer, resultStatusLongMap);
        printPlayerResult(results);
    }

    private static void printDealerResult(Dealer dealer, Map<ResultStatus, Long> dealerResult) {
        System.out.print(String.format("%s: ", dealer.getName()));
        dealerResult.forEach((key, value) ->
                System.out.print(value + key.getName() + " ")
        );
        System.out.println();
    }

    private static void printPlayerResult(Map<Player, ResultStatus> results) {
        results.forEach(
                ((player, status) -> System.out.println(String.format("%s: %s", player.getName(), status.getName()))));
    }

    public static void printError(String message) {
        print(message);
    }

    public static void printBurst() {
        System.out.println("버스트 되었습니다.");
        System.out.println();
    }
}
