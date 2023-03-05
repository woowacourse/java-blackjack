package blackjack.domain.game;


import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private static final int INITIAL_CARD_SIZE = 2;
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
        final Dealer dealer = participants.getDealer();
        giveTwoCard(dealer);

        final List<Player> players = participants.getPlayers();
        players.forEach(this::giveTwoCard);
    }

    private void giveTwoCard(final Participant participant) {
        for(int i = 0; i < INITIAL_CARD_SIZE; i++) {
            participant.drawCard(deck.drawCard());
        }
    }

    public void drawCard(final Participant participant) {
        participant.drawCard(deck.drawCard());
    }
}
