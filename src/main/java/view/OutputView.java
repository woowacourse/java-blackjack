package view;

import domain.MatchResult;
import domain.Player;
import domain.dto.NameAndCards;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printInitialCards(NameAndCards dealer, List<NameAndCards> participants) {
        List<String> names = participants.stream()
                .map(NameAndCards::name)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.name(), String.join(", ", names));

        printPlayerCards(dealer);
        participants.forEach(OutputView::printPlayerCards);
        System.out.println();
    }

    public static void printPlayerCards(NameAndCards player) {
        System.out.printf("%s카드: %s%n", player.name(),
                String.join(", ", player.cards().stream()
                        .map(card -> String.format("%s%s", card.getRank().getTitle(),
                                card.getSuit().getTitle()))
                        .toList()));
    }

    public static void printPlayersCardsAndSum(NameAndCards dealer, List<NameAndCards> participants,
                                               Map<String, Integer> nameAndSum) {
        printPlayerCardsAndSum(nameAndSum, dealer);
        participants.forEach(player -> printPlayerCardsAndSum(nameAndSum, player));
        System.out.println();
    }

    private static void printPlayerCardsAndSum(Map<String, Integer> nameAndSum, NameAndCards player) {
        System.out.printf("%s카드: %s - 결과: %d%n", player.name(),
                String.join(", ", player.cards().stream()
                        .map(card -> String.format("%s%s", card.getRank().getTitle(),
                                card.getSuit().getTitle()))
                        .toList()),
                nameAndSum.get(player.name()));
    }

    public static void printAddCardToDealer() {
        System.out.printf("딜러는 16이하라 한장의 카드를 더 받았습니다.%n%n");
    }

    public static void printGameResult(String dealerName, Map<MatchResult, Integer> dealerResult,
                                       Map<Player, MatchResult> participantsMathResult) {
        System.out.printf("%s: %s%n", dealerName, convertToDealerMatchResultFormat(dealerResult));

        participantsMathResult.forEach((key, value) -> System.out.printf("%s: %s%n", key.getName(), value.getTitle()));
    }

    private static String convertToDealerMatchResultFormat(Map<MatchResult, Integer> dealerResult) {
        StringBuilder sb = new StringBuilder();
        dealerResult.forEach((key, value) ->
                sb.append(String.format("%d%s ", value, key.getTitle()))
        );
        return sb.toString();
    }
}
