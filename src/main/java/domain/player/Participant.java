package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.player.hand.Hand;
import domain.player.hand.Score;
import domain.player.info.ParticipantInfo;
import util.HitOrStay;
import util.Notice;

import java.util.List;

public final class Participant {

    private final ParticipantInfo participantInfo;
    private final Hand hand;

    private Participant(final ParticipantInfo participantInfo, final Hand hand) {
        this.participantInfo = participantInfo;
        this.hand = hand;
    }

    public static Participant of(final ParticipantInfo participantInfo) {
        return new Participant(participantInfo, Hand.create());
    }

    public void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return getCards().size() == 2 && getScore().isMaxScore();
    }

    public boolean isHit(boolean wantHit) {
        return canHit() && wantHit;
    }


    public void playParticipantTurn(final Deck deck, final HitOrStay hitOrStay, final Notice<Participant> notice) {
        while (isHit(hitOrStay.isHit(this))) {
            takeCard(deck.dealCard());
            notice.print(this);
        }
    }

    public boolean canHit() {
        return getScore().isUnderMaxScore();
    }

    public Score getScore() {
        return hand.getScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return participantInfo.getName();
    }

    public int winBet() {
        return participantInfo.winBet(isBlackjack());
    }

    public int loseBet() {
        return participantInfo.loseBet();
    }

    public int returnBet() {
        return participantInfo.returnBet();
    }
}
