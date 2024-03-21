package controller.dto;

import java.util.List;
import model.card.Card;

public record DealerFaceUpResult(List<Card> cards, int hand) {
    public List<String> getCardsAsStrings() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
