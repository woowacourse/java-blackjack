package view;

import domain.DealerDto;
import domain.UserDto;
import domain.card.Card;
import domain.game.DealerResult;
import domain.game.PlayerResults;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public static void showStartStatus(List<UserDto> userDtos, DealerDto dealerDto) {
        printUserNames(userDtos);
        printDealerCard(dealerDto);
        printUserCards(userDtos);
        System.out.println();
    }

    private static void printUserNames(List<UserDto> userDtos) {
        String names = userDtos
                .stream()
                .map(userDto -> userDto.name)
                .collect(Collectors.joining(", "));
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.%n", names);
    }

    private static void printDealerCard(DealerDto dealerDto) {
        System.out.println(dealerDto.name.value() + ": " + dealerDto.visibleCard.getName());
    }

    private static void printUserCards(List<UserDto> userDtos) {
        userDtos.forEach(userDto ->
                System.out.println(joinUserNameAndDeck(userDto.name, userDto.cards))
        );
    }

    public static void printPlayerAndDeck(String name, List<Card> userDeck) {
        System.out.println(joinUserNameAndDeck(name, userDeck));
    }

    private static String joinUserNameAndDeck(String name, List<Card> userDeck) {
        return name
                + "카드: "
                + joinDeck(userDeck);
    }

    private static String joinDeck(List<Card> userDeck) {
        return userDeck.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showCardsAndSum(List<UserDto> userDtos) {
        System.out.println();
        userDtos.forEach(
                userDto -> System.out.println(joinUserNameAndDeck(userDto.name, userDto.cards)
                        + " - 결과: " + userDto.sumOfCards));
    }

    public static void showResult(PlayerResults playerResults, DealerResult dealerResult) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + dealerResult.getResultDetail());

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getName().value() + ": " + result.getResult())));
    }

    public static void printBust(String name, List<Card> userDeck) {
        printPlayerAndDeck(name, userDeck);
        System.out.println("버스트!");
    }
}
