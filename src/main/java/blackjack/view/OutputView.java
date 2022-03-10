package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;

public class OutputView {

    public static void printInitGameMessage(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.", dealerDto.getName(),
            playerDtos.stream().map(PlayerDto::getName).collect(Collectors.joining(", ")));
        System.out.println();
    }

    public static void printOpenCard(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        CardDto dealerOpenCard = dealerDto.getCards().get(0);
        System.out.printf("%s: %s%s", dealerDto.getName(), dealerOpenCard.getCardNumber(),
            dealerOpenCard.getCardPattern());
        System.out.println();
        for (PlayerDto playerDto : playerDtos) {
            printPlayerCards(playerDto);
        }
    }

    public static void printPlayersResult(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        System.out.println();
        printPlayerResult(dealerDto);

        for (PlayerDto playerDto : playerDtos) {
            printPlayerResult(playerDto);
        }
    }

    private static void printPlayerResult(PlayerDto dealerDto) {
        System.out.printf("%s: %s - 결과: %d%n",
            dealerDto.getName(),
            dealerDto.getCards()
                .stream()
                .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                .collect(Collectors.joining(", ")),
            dealerDto.getTotalNumber());
    }

    public static void printPlayerCards(PlayerDto playerDto) {
        System.out.printf("%s: %s%n", playerDto.getName(),
            playerDto.getCards()
                .stream()
                .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                .collect(Collectors.joining(", ")));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

}
