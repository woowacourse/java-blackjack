package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPlayerInitialCards(final List<Player> players, final Dealer dealer) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(), getUserNames(players));
        printPlayerCardStatus(dealer.getName(), dealer.openFirstCards());
        for (Player player : players) {
            printPlayerCardStatus(player.getName(), player.openFirstCards());
        }
        System.out.println();
    }

    public static String getUserNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerCardStatus(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, printCards(cards));
    }

    private static String printCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumber().getType() + card.getCardPattern().getName())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerDrawOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printAllPlayerCardStatus(List<Player> players, Dealer dealer) {
        printPlayerCardStatusAndScore(dealer.getName(), dealer.getCards().getCards(), dealer.calculateScore());
        for (Player player : players) {
            printPlayerCardStatusAndScore(player.getName(), player.getCards().getCards(), player.calculateScore());
        }
    }

    private static void printPlayerCardStatusAndScore(String name, List<Card> cards, int cardScore) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, printCards(cards), cardScore);
    }

    public static void printGameResult(final Map<GameResult, Integer> dealerResultCount,
            final Map<String, GameResult> userResults) {
        System.out.println("\n## 최종 승패");

        System.out.println(printDealerResult(dealerResultCount));

        for (String userName : userResults.keySet()) {
            System.out.println(userName + ": " + userResults.get(userName).getValue());
        }
    }

    private static String printDealerResult(Map<GameResult, Integer> dealerResultCount) {
        StringBuilder stringBuilder = new StringBuilder("딜러: ");
        Arrays.stream(GameResult.values()).forEach(resultType -> {
            int count = dealerResultCount.get(resultType);
            if (count > 0) {
                stringBuilder.append(count).append(resultType.getValue()).append(" ");
            }
        });
        return stringBuilder.toString();
    }
}
