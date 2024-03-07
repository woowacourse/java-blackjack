package model;

import java.util.List;

public record IndividualFaceUpResult(Name name, List<Card> cards, int hand) {
    public String getPartipantNameAsString() {
        return name.getValue();
    }

    public List<String> getCardsAsStrings() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
