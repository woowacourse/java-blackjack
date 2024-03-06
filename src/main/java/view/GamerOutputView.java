package view;

import domain.Card;
import domain.CardName;
import domain.CardType;
import dto.GamerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GamerOutputView {

    // jason카드: 7클로버, K스페이드 - 결과: 17
    public static void print(GamerDTO gamerDTO) {
        String name = gamerDTO.getName();
        List<Card> cards = gamerDTO.getHoldingCards();
        int summationCardPoint = gamerDTO.getSummationCardPoint();

        String nameOutput = name + "카드";
        String cardsOutput = cards.stream()
                .map(card -> mapToString(card.cardType()) + mapToString(card.name()))
                .collect(Collectors.joining(", "));
        String summationCardPointOutput = "결과: %d".formatted(summationCardPoint);
        System.out.printf("%s: %s - %s\n", nameOutput, cardsOutput, summationCardPointOutput);
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
}
