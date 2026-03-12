package dto;

import domain.Card;
import java.util.ArrayList;
import java.util.List;

public record CardDto(String cardShape, String cardContentNumber) {
    public static CardDto from(Card card) {
        String cardShape = card.getCardShape().getName();
        String cardContents = card.getCardContents().getNumber();
        return new CardDto(cardShape, cardContents);
    }

    public static List<CardDto> listOf(List<Card> cards) {
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            CardDto cardDto = CardDto.from(card);
            cardDtos.add(cardDto);
        }
        return cardDtos;
    }
}
