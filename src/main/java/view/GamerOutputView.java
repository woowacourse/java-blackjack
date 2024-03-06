package view;

import domain.Card;
import domain.CardName;
import domain.CardType;
import dto.GamerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GamerOutputView {

    public static void print(GamerDTO gamerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(gamerDTO);
        String summationCardPointOutput = "결과: %d".formatted(gamerDTO.getSummationCardPoint());
        System.out.printf("%s - %s\n", outputWithoutSummationCardPoint, summationCardPointOutput);
    }

    private static String mapToString(CardType cardType) {
        if (cardType == CardType.HEART) {
            return "하트";
        }
        if (cardType == CardType.SPADE) {
            return "스페이드";
        }
        if (cardType == CardType.CLOVER) {
            return "클로버";
        }
        if (cardType == CardType.DIAMOND) {
            return "다이아몬드";
        }
        throw new IllegalArgumentException("잘못된 카드 타입입니다.");
    }

    private static String mapToString(CardName cardName) {
        if (cardName == CardName.ACE) {
            return "A";
        }
        if (cardName == CardName.JACK) {
            return "J";
        }
        if (cardName == CardName.QUEEN) {
            return "Q";
        }
        if (cardName == CardName.KING) {
            return "K";
        }
        return String.valueOf(cardName.getCardNumber());
    }

    public static void printWithoutSummationCardPoint(GamerDTO gamerDTO) {
        String outputWithoutSummationCardPoint = generateOutputWithoutSummationCardPoint(gamerDTO);
        System.out.println(outputWithoutSummationCardPoint);
    }

    private static String generateOutputWithoutSummationCardPoint(GamerDTO gamerDTO) {
        String name = gamerDTO.getName();
        List<Card> cards = gamerDTO.getHoldingCards();
        String nameOutput = name + "카드";
        String cardsOutput = cards.stream()
                .map(card -> mapToString(card.cardType()) + mapToString(card.name()))
                .collect(Collectors.joining(", "));
        return "%s: %s".formatted(nameOutput, cardsOutput);
    }
}
