package blackjack.dto;

import blackjack.domain.deck.Card;
import blackjack.view.mapper.RankMapper;
import blackjack.view.mapper.ShapeMapper;

public record CardDto (String cardName){
    public static CardDto createByCard(Card card) {
        String rankName = RankMapper.findByRank(card.getRank()).getRankName();
        String shapeName = ShapeMapper.findByShape(card.getShape()).getShapeName();
        return new CardDto(rankName + shapeName);
    }
}
