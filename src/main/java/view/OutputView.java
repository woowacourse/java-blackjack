package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.CardContentDto;
import domain.Card;

public final class OutputView {
    public static void displayCardDistribution(List<String> names) {
        String nameContent = String.join(", ", names);
        System.out.printf("딜러가 %s에게 2장을 나누었습니다.\n", nameContent);
    }

    public static void displayCardContent(List<CardContentDto> cardContentDto) {
        for (CardContentDto dto : cardContentDto) {
            List<String> cardContents = new ArrayList<>();
            for (Card card : dto.cards()) {
                cardContents.add(card.getCardRank().getName() + card.getCardShape().getName());
            }

            System.out.printf("%s카드: %s\n", dto.name(), String.join(", ", cardContents));
        }

    }


}
