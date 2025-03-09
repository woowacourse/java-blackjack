package dto;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Participant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record CardDeckStatusResponse(Map<String, List<String>> cardDeckNamesOfParticipant) {

    public static CardDeckStatusResponse from(Map<Participant, CardDeck> cardDeckOfParticipant) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new LinkedHashMap<>();

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            String participantName = entry.getKey().getNickname();
            List<Card> participantCards = entry.getValue().getCards();
            List<String> cardDisplayNames = participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList();

            cardDeckNamesOfParticipant.put(participantName, cardDisplayNames);
        }
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }

    public static CardDeckStatusResponse from(String nickname, CardDeck cardDeck) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new HashMap<>();

        List<Card> participantCards = cardDeck.getCards();
        List<String> cardDisplayNames = participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList();

        cardDeckNamesOfParticipant.put(nickname, cardDisplayNames);
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }
}
