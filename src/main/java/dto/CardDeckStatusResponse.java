package dto;

import domain.card.Card;
import domain.card.ParticipantCardDeck;
import domain.participant.Participant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record CardDeckStatusResponse(Map<String, List<String>> cardDeckNamesOfParticipant) {

    public static CardDeckStatusResponse from(List<Participant> participants) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new LinkedHashMap<>();

        for (Participant participant : participants) {
            String participantName = participant.getNickname();
            List<Card> participantCards = participant.getCardDeck().getCards();
            List<String> cardDisplayNames = participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList();

            cardDeckNamesOfParticipant.put(participantName, cardDisplayNames);
        }

        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }

    public static CardDeckStatusResponse from(String nickname, ParticipantCardDeck participantCardDeck) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new HashMap<>();

        List<Card> participantCards = participantCardDeck.getCards();
        List<String> cardDisplayNames = participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList();

        cardDeckNamesOfParticipant.put(nickname, cardDisplayNames);
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }
}
