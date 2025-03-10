package view;

import controller.dto.DealerMatchResultCount;
import controller.dto.NameAndCards;
import controller.dto.NameAndSums;
import controller.dto.NameAndSums.NameAndSum;
import controller.dto.ParticipantsMatchResult;
import java.util.List;

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
                                               NameAndSums nameAndSums) {
        printPlayerCardsAndSum(dealer, findNameAndSumByName(dealer.name(), nameAndSums.nameAndSums()));
        participants.forEach(player ->
                printPlayerCardsAndSum(player, findNameAndSumByName(player.name(), nameAndSums.nameAndSums())));
        System.out.println();
    }

    private static NameAndSum findNameAndSumByName(String name, List<NameAndSum> nameAndSums) {
        return nameAndSums.stream()
                .filter(nameAndSum -> nameAndSum.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(name + "존재하지 않는 플레이어입니다."));
    }

    private static void printPlayerCardsAndSum(NameAndCards player, NameAndSum nameAndSum) {
        System.out.printf("%s카드: %s - 결과: %d%n", player.name(),
                String.join(", ",
                        player.cards().stream()
                                .map(card -> String.format("%s%s", card.getRank().getTitle(),
                                        card.getSuit().getTitle()))
                                .toList()),
                nameAndSum.sum());
    }

    public static void printAddCardToDealer() {
        System.out.printf("딜러는 16이하라 한장의 카드를 더 받았습니다.%n%n");
    }

    public static void printMatchResults(String dealerName, DealerMatchResultCount dealerResult,
                                         ParticipantsMatchResult participantsMathResult) {
        System.out.printf("%s: %s%n", dealerName, convertToDealerMatchResultFormat(dealerResult));

        participantsMathResult.participantMatchResult()
                .forEach((key, value) -> System.out.printf("%s: %s%n", key.getName(), value.getTitle()));
    }

    private static String convertToDealerMatchResultFormat(DealerMatchResultCount dealerResult) {
        StringBuilder sb = new StringBuilder();
        dealerResult.matchResultCount().forEach((key, value) ->
                sb.append(String.format("%d%s ", value, key.getTitle()))
        );
        return sb.toString();
    }
}
