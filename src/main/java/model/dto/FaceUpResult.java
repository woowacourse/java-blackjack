package model.dto;

import java.util.List;
import model.card.Card;
import model.participant.Name;

public record FaceUpResult(Name name, List<Card> cards, int hand) {
    public String getPartipantNameAsString() {
        return name.getValue();
    }

    public List<String> getCardsAsStrings() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
