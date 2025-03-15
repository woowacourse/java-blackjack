package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public void giveStartingCards() {
        participants.getParticipants().forEach(this::giveStartingCards);
    }

    private void giveStartingCards(Participant participant) {
        List<Card> cards = deck.takeStartingCards();
        cards.forEach(participant::takeCard);
    }

    public void giveMoreCard(Participant participant) {
        Card card = deck.takeSingleCard();
        participant.takeCard(card);
    }

    public Participants getParticipants() {
        return participants;
    }
}
