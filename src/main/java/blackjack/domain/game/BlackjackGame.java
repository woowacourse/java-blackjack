package blackjack.domain.game;


import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public Participants getParticipants() {
        return participants;
    }

    public void giveTwoCardEveryone() {
        Dealer dealer = participants.getDealer();
        giveTwoCard(dealer);

        List<Player> players = participants.getPlayers();
        players.forEach(this::giveTwoCard);
    }

    private void giveTwoCard(Participant participant) {
        participant.drawCard(deck.drawCard());
        participant.drawCard(deck.drawCard());
    }

    public void drawCard(Participant participant) {
        participant.drawCard(deck.drawCard());
    }
}
