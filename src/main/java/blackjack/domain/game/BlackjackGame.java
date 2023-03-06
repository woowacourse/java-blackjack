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

    public void initialCardsToAllParticipant() {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        drawCard(dealer, 2);
        players.forEach(player -> drawCard(player, 2));
    }

    public void drawCard(final Participant participant) {
        drawCard(participant, 1);
    }

    public void drawCard(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            participant.drawCard(deck.drawCard());
        }
    }
}
