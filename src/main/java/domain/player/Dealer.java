package domain.player;

import domain.card.Card;

import java.util.List;

public final class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST = 0;
    private static final int STAY_SCORE = 17;

    private final Name name;
    private final Hand hand;

    public Dealer(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME), Hand.create());
    }

    public void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public List<Card> showCards() {
        return List.of(getHand().get(FIRST));
    }

    public boolean canHit() {
        return score().isUnderThan(STAY_SCORE);
    }


    public boolean isBust() {
        return hand.isBust();
    }

    public  List<Card> getHand() {
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
