package view;

import domain.money.Profit;
import domain.user.Player;
import dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    public static void showStartStatus(List<UserDto> userDtos, UserDto dealerDto) {
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

    private static void printDealerCard(UserDto dealerDto) {
        System.out.println(dealerDto.name + ": " + dealerDto.getFirstCard());
    }

    private static void printUserCards(List<UserDto> userDtos) {
        userDtos.forEach(userDto ->
                System.out.println(joinUserNameAndDeck(userDto.name, userDto.cards))
        );
    }

    public static void printPlayerAndDeck(UserDto userDto) {
        System.out.println(joinUserNameAndDeck(userDto.name, userDto.cards));
    }

    private static String joinUserNameAndDeck(String name, List<String> userDeck) {
        return name
                + "카드: "
                + joinDeck(userDeck);
    }

    private static String joinDeck(List<String> userDeck) {
        return String.join(", ", userDeck);
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

    public static void printBust(UserDto userDto) {
        printPlayerAndDeck(userDto);
        System.out.println("버스트!");
    }

    public static void showProfit(Map<Player, Profit> profitManager, Profit profitOfDealer) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + profitOfDealer.getValue());
        profitManager.forEach((player, profit) -> System.out.println(player.getName() + ": " + profit.getValue()));
    }
}
