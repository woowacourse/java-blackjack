package response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import object.card.Card;
import object.card.CardDeck;
import object.participant.Participant;

public record CardDeckStatusResponse(Map<String, List<String>> cardDeckNamesOfParticipant) {

    public static CardDeckStatusResponse generateInitCardResponse(Map<Participant, CardDeck> cardDeckOfParticipant) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new LinkedHashMap<>();

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            CardDeck ownedCardDeck = entry.getValue();

            String participantName = participant.getNickname();
            List<Card> participantCards = new ArrayList<>(ownedCardDeck.getCards());

            List<String> cardDisplayNames = new ArrayList<>(participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList());

            if (participant.isDealer()) {
                cardDisplayNames.removeLast();
                cardDisplayNames.add("\uD83C\uDCA0?");
                cardDeckNamesOfParticipant.put(participantName, cardDisplayNames);
                continue;
            }

            cardDeckNamesOfParticipant.put(participantName, cardDisplayNames);
        }

        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }

    public static CardDeckStatusResponse of(String nickname, CardDeck cardDeck) {
        Map<String, List<String>> cardDeckNamesOfParticipant = new HashMap<>();

        List<Card> participantCards = cardDeck.getCards();
        List<String> cardDisplayNames = participantCards.stream().map(card -> card.getName() + card.getCardSymbol()).toList();

        cardDeckNamesOfParticipant.put(nickname, cardDisplayNames);
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }
}
