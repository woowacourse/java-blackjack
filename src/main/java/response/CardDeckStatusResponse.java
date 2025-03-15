package response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import object.card.Card;
import object.card.CardDeck;
import object.game.BlackJackBoard;
import object.participant.Participant;

public record CardDeckStatusResponse(Map<String, List<String>> cardDeckNamesOfParticipant) {

    public static CardDeckStatusResponse makeInitCardsResponseFrom(BlackJackBoard blackJackBoard) {
        Map<Participant, CardDeck> cardDeckOfParticipant = blackJackBoard.getCardDeckOfParticipant();
        Map<String, List<String>> cardDeckNamesOfParticipant = new LinkedHashMap<>();

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            CardDeck ownedCardDeck = entry.getValue();

            String participantName = participant.getNickname();
            List<Card> participantCards = new ArrayList<>(ownedCardDeck.getCards());

            List<String> cardDisplayNames = new ArrayList<>(participantCards.stream().map(Card::getName).toList());

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

    public static CardDeckStatusResponse makeResponseOf(Participant participant, BlackJackBoard blackJackBoard) {
        String nickname = participant.getNickname();
        CardDeck cardDeck = blackJackBoard.getCardDeckOf(participant);

        Map<String, List<String>> cardDeckNamesOfParticipant = new HashMap<>();

        List<Card> participantCards = cardDeck.getCards();
        List<String> cardDisplayNames = participantCards.stream().map(Card::getName).toList();

        cardDeckNamesOfParticipant.put(nickname, cardDisplayNames);
        return new CardDeckStatusResponse(cardDeckNamesOfParticipant);
    }
}
