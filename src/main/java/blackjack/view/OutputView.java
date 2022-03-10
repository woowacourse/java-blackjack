package blackjack.view;

import blackjack.domain.GamerDto;
import blackjack.domain.card.Card;
import blackjack.domain.result.BlackJackResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printFirstCards(GamerDto dealer, List<GamerDto> players) {
        StringBuilder builder = new StringBuilder();
        printGamers(dealer, players, builder);
        printCards(dealer, players, builder);
        System.out.println(builder);
    }

    private static void printGamers(GamerDto dealer, List<GamerDto> players, StringBuilder builder) {
        builder.append(dealer.getName() + "와 ");
        String names = players.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(", "));
        builder.append(names + "에게 " + dealer.getCardSize() + "장을 나누었습니다.\n");
    }

    private static void printCards(GamerDto dealer, List<GamerDto> players, StringBuilder builder) {
        Card firstCard = dealer.getFirstCard();
        String dealerFirstCardName = firstCard.getValue() + firstCard.getShape();
        builder.append(dealer.getName() + "카드: " + dealerFirstCardName + "\n");

        for (GamerDto player : players) {
            String playerCard = appendPlayerCard(player);
            builder.append(playerCard);
        }
    }

    private static String getCardNames(GamerDto dealer) {
        return dealer.getCards().stream()
                .map(card -> card.getName() + card.getShape())
                .collect(Collectors.joining(", "));
    }

    public static void printFinalCards(GamerDto dealer, List<GamerDto> players) {
        StringBuilder builder = new StringBuilder();
        builder.append(dealer.getName() + "카드: ");
        builder.append(getCardNames(dealer));
        builder.append("- 결과: " + dealer.getCardNumberSum() + "\n");

        for (GamerDto player : players) {
            builder.append(player.getName() + "카드: ");
            builder.append(getCardNames(player));
            builder.append("- 결과: " + player.getCardNumberSum() + "\n");
        }
        System.out.println(builder);
    }

    public static void printPlayerCard(GamerDto player) {
        System.out.print(appendPlayerCard(player));
    }

    private static String appendPlayerCard(GamerDto player) {
        StringBuilder builder = new StringBuilder();
        builder.append(player.getName() + "카드: ");
        builder.append(getCardNames(player) + "\n");
        return builder.toString();
    }

    public static void printAdditionalDrawDealer(int count) {
        System.out.println();
        if (count != 0) {
            System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", count);
            return;
        }
        System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n\n");
    }

    public static void printFinalResult(Map<BlackJackResult, Integer> dealerResult, Map<String, BlackJackResult> playerResult) {
        System.out.println("## 최종 승패");
        StringBuilder builder = new StringBuilder("딜러: ");
        for (BlackJackResult dealer : dealerResult.keySet()) {
            if (dealerResult.get(dealer) > 0) {
                builder.append(dealerResult.get(dealer) + dealer.getValue());
            }
        }
        builder.append("\n");

        for (String name : playerResult.keySet()) {
            builder.append(name+": ");
            builder.append(playerResult.get(name).getValue() + "\n");
        }
        System.out.print(builder);
    }
}
