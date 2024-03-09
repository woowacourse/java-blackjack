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
        System.out.println(dealOutMessage(dealer, players));

        System.out.println(dealerSingleCard(dealer));
        players.forEach(OutputView::printTotalHand);
        System.out.println();
    }

    public static void printTotalHand(Player player) {
        System.out.println(allCardInHand(player));
    }

    public static void printHandWithScore(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(allCardInHand(dealer) + resultScore(dealer));
        players.forEach(
                player -> System.out.println(allCardInHand(player) + resultScore(player)));
    }

    public static void printResult(Result result, Dealer dealer) {
        Map<Player, ResultStatus> results = result.getResults();
        Map<ResultStatus, Long> resultStatusLongMap = result.calculateDealerResult();

        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(dealer, resultStatusLongMap);
        printPlayerResult(results);
    }

    public static void printBurst() {
        System.out.println("버스트 되었습니다.");
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    private static String allCardInHand(Player player) {
        List<Card> cards = player.getHand().getCards();
        String hand = cards.stream()
                .map(OutputView::extractCardName)
                .collect(Collectors.joining(", "));

        return String.format("%s: %s", player.getName(), hand);
    }

    private static String dealerSingleCard(Dealer dealer) {
        List<Card> cards = dealer.getHand().getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }

        return String.format("%s: %s", dealer.getName(), extractCardName(cards.get(0)));
    }

    private static String dealOutMessage(Dealer dealer, List<Player> players) {
        String commaSeparatePlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        return String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), commaSeparatePlayerNames);
    }

    private static String extractCardName(Card card) {
        return card.getNumber().getName() + card.getSymbol().getName();
    }

    private static String resultScore(Player player) {
        int score = player.calculate();
        return String.format(" - 결과: %d", score);
    }

    private static void printDealerResult(Dealer dealer, Map<ResultStatus, Long> dealerResult) {
        System.out.printf("%s: ", dealer.getName());
        dealerResult.forEach((key, value) ->
                System.out.print(value + key.getName() + " "));
        System.out.println();
    }

    private static void printPlayerResult(Map<Player, ResultStatus> results) {
        results.forEach(
                ((player, status) -> System.out.printf("%s: %s%n", player.getName(), status.getName())));
    }
}
