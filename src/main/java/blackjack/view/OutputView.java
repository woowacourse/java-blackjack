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
            System.out.printf("%s: %s", playerDto.getName(),
                playerDto.getCards()
                    .stream()
                    .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }
    }

}
