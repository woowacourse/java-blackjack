package controller;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class GameResponseRenderer {

    public static String rendCardStatus(final List<CardResponse> cardResponses) {
        return cardResponses.stream()
                .map(cardResponse -> cardResponse.getName() + cardResponse.getShape())
                .collect(joining(", "));
    }

    public static String rendDealerFirstCardStatus(final List<CardResponse> cardResponses) {
        final CardResponse cardResponse = cardResponses.get(0);
        return cardResponse.getName() + cardResponse.getShape();
    }
}
