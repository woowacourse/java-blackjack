package view;

import domain.money.Money;
import domain.card.Card;
import domain.dto.CardNames;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printDealerCardWithHidden(final Card card) {
        System.out.println("딜러: " + getCardName(card));
    }

    public static void printPlayerCards(final Map<String, CardNames> playerToCard) {
        playerToCard.forEach((playerName, cards) ->
                System.out.println(getEachPlayerCards(playerName, cards)));
        System.out.println();
    }

    public static void printDealerCardWithScore(final CardNames cards, final int score) {
        printCardWithScore("딜러 ", cards, score);
    }

    public static void printPlayerCardWithScore(final Map<String, CardNames> playerToCard,
                                                final Map<String, Integer> playerToScore) {
        playerToCard.forEach((playerName, cards) -> {
            int score = playerToScore.get(playerName);
            printCardWithScore(playerName, cards, score);
        });
        System.out.println();
    }

    public static void printEachPlayerCards(final String playerName, final CardNames cards) {
        System.out.println(getEachPlayerCards(playerName, cards));
    }

    private static void printCardWithScore(final String playerName, final CardNames cards, final int score) {
        System.out.println(getEachPlayerCards(playerName, cards) + " - 결과: " + score);
    }

    private static String getEachPlayerCards(final String playerName, final CardNames cardNames) {
        String stringBuilder = playerName + "카드: "
                + String.join(", ", cardNames.getCardNames());
        return stringBuilder;
    }

    private static String getCardName(final Card card) {
        return card.getCardName();
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printInitHitMessage(final List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printWinningMoneyOfPlayers(Money winningMoneyOfDealer,
                                                  Map<String, Money> winningMoneyOfPlayers) {
        System.out.println("## 최종 수익");
        printWinningMoneyOfUser("딜러", winningMoneyOfDealer);
        for (String playerName : winningMoneyOfPlayers.keySet()) {
            printWinningMoneyOfUser(playerName, winningMoneyOfPlayers.get(playerName));
        }
    }

    private static void printWinningMoneyOfUser(String userName, Money userWinningMoney) {
        System.out.printf("%s: %d", userName, userWinningMoney.getValue());
        System.out.println();
    }
}
