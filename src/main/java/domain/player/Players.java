package domain.player;

import domain.card.Deck;
import util.HitOrStay;
import util.Notice;

import java.util.List;

public class Players {

    private final Dealer dealer;
    private final Participants participants;

    private Players(final Dealer dealer, final Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public static Players of(final List<Participant> participants) {
        return new Players(Dealer.create(), Participants.of(participants));
    }

    public void drawCards(final Deck deck) {
        dealer.takeCard(deck.dealCard());
        dealer.takeCard(deck.dealCard());

        participants.takeCard(deck);
        participants.takeCard(deck);
    }

    public void playParticipantsTurn(final Deck deck, final HitOrStay hitOrStay, final Notice<Participant> notice) {
        participants.playParticipantsTurn(deck, hitOrStay, notice);
    }

    public void playDealerTurn(final Deck deck, final Notice<Boolean> notice) {
        while (dealer.canHit()) {
            notice.print(dealer.canHit());
            dealer.takeCard(deck.dealCard());
        }
        notice.print(dealer.canHit());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
