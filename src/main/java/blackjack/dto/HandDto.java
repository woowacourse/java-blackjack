package blackjack.dto;

import blackjack.domain.gamer.Hand;
import java.util.List;

public record HandDto(List<CardDto> cardDtos, int handScore) {

    public static HandDto fromHand(Hand hand) {
        List<CardDto> cardDtos = hand.getCards().stream()
                .map(CardDto::fromCard)
                .toList();

        return new HandDto(cardDtos, hand.generateValue().getScore());
    }
}
