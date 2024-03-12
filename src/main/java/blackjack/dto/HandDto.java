package blackjack.dto;

import blackjack.domain.cards.Hand;
import java.util.List;

public record HandDto(List<String> cardNames) {
    public static HandDto from(Hand hand) {
        List<String> cardNameList = hand.getCardsValue().stream()
                .map(CardDto::from)
                .map(CardDto::cardName)
                .toList();
        return new HandDto(cardNameList);
    }
}
