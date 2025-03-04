package view;

import controller.dto.DealerMatchResultCountDto;
import controller.dto.NameAndCardsDto;
import controller.dto.NameAndSumsDto;
import controller.dto.NameAndSumsDto.NameAndSumDto;
import controller.dto.UsersMatchResultDto;
import domain.card.Card;
import java.util.List;

public class OutputView {
    public static void printInitialCards(NameAndCardsDto dealer, List<NameAndCardsDto> users) {
        List<String> names = users.stream()
                .map(NameAndCardsDto::name)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.name(), String.join(", ", names));

        printPlayerCards(dealer.name(), dealer.cards());
        users.forEach(user -> printPlayerCards(user.name(), user.cards()));
        System.out.println();
    }

    public static void printPlayerCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name,
                String.join(", ", cards.stream()
                        .map(card -> String.format("%s%s", card.getRank().getTitle(),
                                card.getSuit().getTitle()))
                        .toList()));
    }

    public static void printPlayersCardsAndSum(NameAndCardsDto dealer,
                                               List<NameAndCardsDto> users,
                                               NameAndSumsDto nameAndSumsDto) {
        printPlayerCardsAndSum(dealer, findNameAndSumByName(dealer.name(), nameAndSumsDto.nameAndSums()));
        users.forEach(player ->
                printPlayerCardsAndSum(player, findNameAndSumByName(player.name(), nameAndSumsDto.nameAndSums())));
        System.out.println();
    }

    private static NameAndSumDto findNameAndSumByName(String name, List<NameAndSumDto> nameAndSums) {
        return nameAndSums.stream()
                .filter(nameAndSum -> nameAndSum.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(name + "존재하지 않는 플레이어입니다."));
    }

    private static void printPlayerCardsAndSum(NameAndCardsDto player, NameAndSumDto nameAndSum) {
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

    public static void printMatchResults(String dealerName, DealerMatchResultCountDto dealerResult,
                                         UsersMatchResultDto usersMathResult) {
        System.out.printf("%s: %s%n", dealerName, convertToDealerMatchResult(dealerResult));

        usersMathResult.nameMatchResult()
                .forEach((key, value) -> System.out.printf("%s: %s%n", key, value.getTitle()));
    }

    private static String convertToDealerMatchResult(DealerMatchResultCountDto dealerResult) {
        StringBuilder sb = new StringBuilder();
        dealerResult.matchResultCount().forEach((key, value) ->
                sb.append(String.format("%d%s ", value, key.getTitle()))
        );
        return sb.toString();
    }
}
