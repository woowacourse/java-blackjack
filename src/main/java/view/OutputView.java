package view;

import common.GamerDto;
import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final int FIRST_CARD_INDEX = 0;

    public static void printInitGamersState(GamerDto dealerDto, List<GamerDto> playerDtos) {
        String dealerName = dealerDto.getName();
        String playerNames = playerDtos.stream().map(GamerDto::getName).collect(Collectors.joining(DELIMITER));
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.\n", dealerName, playerNames);
        printInitDealerCard(dealerDto);
        for (GamerDto playerDto : playerDtos) {
            printGamerCardsState(playerDto);
        }
    }

    private static void printInitDealerCard(GamerDto dealerDto) {
        Card card =  dealerDto.getCards().get(FIRST_CARD_INDEX);
        System.out.printf("%s: %s%s\n", dealerDto.getName(), card.getSymbol().getWord(), card.getType().getPattern());
    }

    public static void printGamerCardsState(GamerDto gamerDto) {
        String gamerCards = gamerDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s: %s\n", gamerDto.getName(), gamerCards);
    }

    public static void printDealerHit() {
        System.out.println("딜러 16이하라 한 장의 카드를 더 받습니다.");
    }
}
