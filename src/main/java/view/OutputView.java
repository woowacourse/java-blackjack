package view;

import domain.MatchResult;
import domain.Player;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printInitialCards(Player dealer, List<Player> participants) {
        List<String> names = participants.stream()
                .map(Player::getName)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));
    }

    public static void printPlayerCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(),
                String.join(", ", player.getCards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));
    }

    public static void printPlayersCardsAndSum(Player dealer, List<Player> participants,
                                               Map<String, Integer> nameAndSum) {
        printPlayerCardsAndSum(nameAndSum, dealer);
        participants.forEach(player -> printPlayerCardsAndSum(nameAndSum, player));
        System.out.println();
    }

    private static void printPlayerCardsAndSum(Map<String, Integer> nameAndSum, Player player) {
        System.out.printf("%s카드: %s - 결과: %d%n", player.getName(),
                String.join(", ", player.getCards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()),
                nameAndSum.get(player.getName()));
    }

    public static void printAddCardToDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public static void printMatchResult(Map<MatchResult, Integer> dealerResult, Map<String, MatchResult> participantsResult) {
        System.out.printf("딜러: %s%n", convertToDealerMatchResultFormat(dealerResult));

        participantsResult.forEach((key, value) -> System.out.printf("%s: %s%n", key, value.getTitle()));
    }

    private static String convertToDealerMatchResultFormat(Map<MatchResult, Integer> dealerResult) {
        StringBuilder sb = new StringBuilder();
        dealerResult.forEach((key, value) -> {
            sb.append(String.format("%d%s ", value, key.getTitle()));
        });
        return sb.toString();
    }
}
