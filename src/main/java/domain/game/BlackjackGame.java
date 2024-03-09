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

    public static final int STARTING_CARDS_AMOUNT = 2;

    private final Participants participants;
    private final DeckCards deckCards;

    public BlackjackGame(Participants participants, CardGenerator cardGenerator) {
        this.participants = participants;
        this.deckCards = DeckCards.from(cardGenerator);
    }

    public void distributeStartingCards() {
        for (Participant participant : participants.getParticipants()) {
            List<Card> cards = deckCards.drawCards(STARTING_CARDS_AMOUNT);
            participant.receive(cards);
        }
    }

    public void giveOneCard(Participant participant) {
        if (participants.doesNotContain(participant)) {
            throw new IllegalArgumentException("[ERROR] 해당 게임의 참여자가 아닙니다.");
        }
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
