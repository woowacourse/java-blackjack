package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class OutputView {

    private static final String CARD_HANDOUT_MESSAGE_FORMAT = "\n%s와 %s에게 카드 2장을 나누었습니다.\n";
    private static final String HAND_CARD_MESSAGE_FORMAT = "%s카드: %s\n";
    private static final String RESULT_MESSAGE_FORMAT = "%s카드: %s - 결과: %s\n";

    public static void printInitialStatus(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        printCardHandOutMessage(dealer.getName(), playerNames);
        printOpenedCards(dealer, players);
        System.out.println();
    }

    public static void printCurrentCards(Player player) {
        System.out.printf(HAND_CARD_MESSAGE_FORMAT, player.getName(), join(format(player.getCards())));
    }

    public static void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(Dealer dealer, List<Player> players) {
        System.out.printf(RESULT_MESSAGE_FORMAT, dealer.getName(), join(format(dealer.getCards())), dealer.getScore());
        for (Player player : players) {
            System.out.printf(RESULT_MESSAGE_FORMAT, player.getName(), join(format(player.getCards())), player.getScore());
        }
    }

    public static void printResult(Map<String, String> playerResults, List<String> dealerResult) {
        System.out.println("\n## 최종 승패");
        System.out.println(makeDealerResultString(dealerResult));

        playerResults.forEach(
                (name, result) -> System.out.println(name + ": " + result)
        );
    }

    private static void printCardHandOutMessage(String dealerName, List<String> playerNames) {
        System.out.printf(CARD_HANDOUT_MESSAGE_FORMAT, dealerName, join(playerNames));
    }

    private static void printOpenedCards(Dealer dealer, List<Player> players) {
        System.out.printf(HAND_CARD_MESSAGE_FORMAT, dealer.getName(), join(format(dealer.openCards())));
        for (Player player : players) {
            System.out.printf(HAND_CARD_MESSAGE_FORMAT, player.getName(), join(format(player.openCards())));
        }
    }

    private static String makeDealerResultString(List<String> dealerResult) {
        Map<String, Long> countMap = dealerResult.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        return "딜러 : " + countMap.getOrDefault("승", 0L) + "승 " +
                countMap.getOrDefault("패", 0L) + "패";
    }

    private static String join(List<String> strings) {
        return String.join(", ", strings);
    }

    private static List<String> format(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenominationName() + card.getSymbolName())
                .collect(Collectors.toList());
    }
}
