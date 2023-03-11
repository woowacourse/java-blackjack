package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.player.hand.Hand;
import domain.player.hand.Score;
import domain.player.info.PlayerInfo;
import util.HitOrStay;
import util.Notice;

import java.util.List;

public final class Participant {

    private final PlayerInfo playerInfo;
    private final Hand hand;

    private Participant(final PlayerInfo playerInfo, final Hand hand) {
        this.playerInfo = playerInfo;
        this.hand = hand;
    }

    public static Participant of(final PlayerInfo playerInfo) {
        return new Participant(playerInfo, Hand.create());
    }

    public void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
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

    private boolean canHit() {
        return score().isUnderMaxScore();
    }

    public Score score() {
        return hand.getScore();
    }

    public int getScore() {
        return score().getScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return playerInfo.getName();
    }

    public int getBetAmount() {
        return playerInfo.getBetAmount();
    }

}
