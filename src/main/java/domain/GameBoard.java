package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Participant, CardDeck> cardDeckOfParticipant;
    private final CardDeck gameCardDeck;

    public GameBoard(final CardDeck gameCardDeck, final List<Participant> participants) {
        this.gameCardDeck = gameCardDeck;
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    private Map<Participant, CardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> CardDeck.generateEmptySet()));
    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck cardDeck = entry.getValue();

            Card firstCard = gameCardDeck.draw();
            Card secondCard = gameCardDeck.draw();
            cardDeck.addCard(firstCard);
            cardDeck.addCard(secondCard);
        }
    }

    public Map<Participant, CardDeck> getCardDeckOfParticipant() {
        return cardDeckOfParticipant;
    }
}
