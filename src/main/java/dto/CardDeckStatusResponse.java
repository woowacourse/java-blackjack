package dto;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record CardDeckStatusResponse(Map<String, List<String>> cardDeckNamesOfParticipant) {

    public static CardDeckStatusResponse from(Map<Participant, CardDeck> cardDeckOfParticipant) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new HashMap<>();

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            String participantName = entry.getKey().getName();
            List<Card> participantCards = entry.getValue().getCards();
            List<String> cardDisplayNames = participantCards.stream().map(card -> card.getNumber() + card.getCardSymbol()).toList();

            cardDeckNamesOfParticipant.put(participantName, cardDisplayNames);
        }
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }
}
