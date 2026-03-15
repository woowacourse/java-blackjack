package blackjack.domain;

public class Dealer extends Participant {

    private static final int DEALER_STAND_POINT = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public void recieveCard(Card card) {
        addCard(card);
    }

    public String getFirstCardNames() {
        return getFirstCardName();
    }


    @Override
    public boolean shouldDraw() {
        return getTotalPoint() < DEALER_STAND_POINT;
    }


}
