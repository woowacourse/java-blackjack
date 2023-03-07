package blackjack.domain.game;


import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private static final int START_CARD_DRAW_COUNT = 2;
    private static final int ONE = 1;
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

        drawCard(dealer, START_CARD_DRAW_COUNT);
        players.forEach(player -> drawCard(player, START_CARD_DRAW_COUNT));
    }

    public void drawCard(final Participant participant) {
        drawCard(participant, ONE);
    }

    public void drawCard(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            participant.drawCard(deck.drawCard());
        }
    }
}
