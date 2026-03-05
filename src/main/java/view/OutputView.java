package view;

import constant.GameConstant;
import controller.CardContentDto;
import domain.Card;

import java.util.ArrayList;
import java.util.List;

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

    public static void displayCardContent(CardContentDto dto) {
        List<String> cardContents = new ArrayList<>();
        for (Card card : dto.cards()) {
            cardContents.add(card.getCardRank().getName() + card.getCardShape().getName());
        }

        System.out.printf("%s카드: %s\n", dto.name(), String.join(", ", cardContents));
    }

    public static void displayDealerCard() {
        System.out.println("딜러는 " + GameConstant.ADDITIONAL_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }
}
