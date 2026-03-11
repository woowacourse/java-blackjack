package dto;

import domain.Hand;
import domain.card.Card;
import java.util.List;

public record HandDto(List<String> cards) {

    public HandDto(Hand hand) {
        this(hand.getCards().stream()
                .map(Card::getCardName)
                .toList());
    }
}
