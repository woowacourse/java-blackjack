package domain.player;

import domain.card.Card;
import domain.player.hand.Hand;
import domain.player.hand.Score;
import domain.player.info.PlayerInfo;

import java.util.List;

public final class Participant {

    private final PlayerInfo playerInfo;
    private final Hand hand;

    public Participant(final PlayerInfo playerInfo, final Hand hand) {
        this.playerInfo = playerInfo;
        this.hand = hand;
    }

    public static Participant of(final String name, final int betAmount) {
        validateImpersonate(name);

        return new Participant(PlayerInfo.of(name, betAmount), Hand.create());
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

    private boolean canHit() {
        return score().isUnderMaxScore();
    }

    private static void validateImpersonate(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
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
