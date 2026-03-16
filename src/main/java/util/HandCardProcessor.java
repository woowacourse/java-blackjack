package util;

import domain.card.Card;

import java.util.List;

public class HandCardProcessor {
    private HandCardProcessor() {
    }

    public static List<String> processHandCards(List<Card> handCards) {
        return handCards.stream()
                .map(CardMapper::cardToKorean)
                .toList();
    }
}
