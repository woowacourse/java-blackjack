package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public static void printUserCards(PlayerDto playerDto) {
        System.out.print(playerDto.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(", ");
        playerDto.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.println(stringJoiner.toString());
    }

    public static void printGiveTwoCardsMessage(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        playerDtos.forEach(playerDto -> stringJoiner.add(playerDto.getName()));
        System.out.println("딜러와 " + stringJoiner.toString() + "에게 2장을 나누었습니다.");
        printUserCards(dealerDto);
        playerDtos.forEach(OutputView::printUserCards);
        System.out.println();
    }

    public static void printDealerDrawCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
