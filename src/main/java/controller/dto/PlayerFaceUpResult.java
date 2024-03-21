package controller.dto;

import java.util.List;
import model.card.Card;
import model.participant.Name;

public record PlayerFaceUpResult(Name name, List<Card> cards, int hand) {
    public String getPartipantNameAsString() {
        return name.getValue();
    }

    public List<String> getCardsAsStrings() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
