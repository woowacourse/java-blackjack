package blackjack.dto;

import blackjack.domain.Cards.Card;

public record CardDto(String cardName) {
    public static CardDto from(Card card) {
        String rankName = RankDto.from(card.getRank()).getRankName();
        String shapeName = ShapeDto.from(card.getShape()).getShapeName();
        return new CardDto(rankName + shapeName);
    }
}
