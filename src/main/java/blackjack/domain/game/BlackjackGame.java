package blackjack.domain.game;


import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private final Deck deck;

    public BlackjackGame(final Deck deck) {
        this.deck = deck;
    }


    public void giveTwoCardEveryone(Participants participants) {
        final Dealer dealer = participants.getDealer();
        giveTwoCard(dealer);

        final List<Player> players = participants.getPlayers();
        players.forEach(this::giveTwoCard);
    }

    private void giveTwoCard(final Participant participant) {
        participant.drawCard(deck.drawCard());
        participant.drawCard(deck.drawCard());
    }

    public void drawCard(final Participant participant) {
        participant.drawCard(deck.drawCard());
    }

    public boolean isPlayerCanPlay(final Participant participant, final boolean order) {
        if (participant.isBust() || !order) {
            return false;
        }
        participant.drawCard(deck.drawCard());
        return participant.isNotBust();
    }

    public boolean playDealer(Dealer dealer) {
        if (dealer.canNotHit()) {
            return false;
        }
        dealer.drawCard(deck.drawCard());
        return true;
    }
}
