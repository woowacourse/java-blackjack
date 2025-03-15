package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;

public record CardInfoDto(String name, List<String> cardInfos) {

    public static CardInfoDto from(final String name, final List<Card> cards) {
        List<String> cardInfos = cards.stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();

        return new CardInfoDto(name, cardInfos);
    }

}
