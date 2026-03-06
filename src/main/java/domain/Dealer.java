package domain;

import java.util.List;

//추상 클래스
public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int BOUNDARY = 16;

    public Dealer(Hand hand) {
        super(NAME, hand);
    }

    public boolean needsToHit() {
        return super.getHand().getTotalScore() <= BOUNDARY;
    }

    public List<Card> getCards(){
        return super.getCards();
    }

    public int getBoundary() {
        return BOUNDARY;
    }
}
