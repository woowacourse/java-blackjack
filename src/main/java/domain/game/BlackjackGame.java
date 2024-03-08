package domain.game;

import domain.card.Card;
import domain.card.DeckCards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import strategy.CardGenerator;

public class BlackjackGame {

    private final Participants participants;
    private final DeckCards deckCards;

    public BlackjackGame(Participants participants, CardGenerator cardGenerator) {
        this.participants = participants;
        this.deckCards = DeckCards.from(cardGenerator);
    }

    public void distributeStartingCards() {
        for (Participant participant : participants.getParticipants()) {
            List<Card> cards = deckCards.drawStartingCards();
            participant.receive(cards);
        }
    }

    public void giveOneCard(Participant participant) {
        if (participant.isReceivable()) {
            participant.receive(deckCards.draw());
        }
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
