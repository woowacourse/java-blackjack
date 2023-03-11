package domain.player;

import domain.card.Card;

import java.util.List;

public final class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Participant of(final String name) {
        validateImpersonate(name);

        return new Participant(Name.of(name), Hand.create());
    }


    public boolean isHit(boolean wantHit) {
        return canHit() && wantHit;
    }

    public boolean canHit() {
        return score().isUnderMaxScore();
    }

    private static void validateImpersonate(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
    }


    public boolean isBust() {
        return hand.isBust();
    }

    public void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Score score() {
        return hand.getScore();
    }

    public int getScore() {
        return score().getScore();
    }

    public String getName() {
        return name.getName();
    }
}
