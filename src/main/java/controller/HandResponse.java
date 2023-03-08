package controller;

import model.card.Card;

import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class HandResponse {

    private final int totalValue;
    private final List<CardResponse> cardResponse;

    private HandResponse(final int totalValue, final List<CardResponse> cardResponses) {
        this.totalValue = totalValue;
        this.cardResponse = cardResponses;
    }

    public static HandResponse of(final int totalValue, final List<Card> cards) {
        return new HandResponse(totalValue, createCardResponses(cards));
    }

    private static List<CardResponse> createCardResponses(final List<Card> cards) {
        return cards.stream()
                .map(card -> new CardResponse(card.getName(), card.getShape()))
                .collect(toUnmodifiableList());
    }

    public int getTotalValue() {
        return totalValue;
    }

    public List<CardResponse> getCardResponse() {
        return unmodifiableList(cardResponse);
    }
}
