package blackjack.domain;

public class Dealer extends Participant {

    private static final int DEALER_STAND_POINT = 17;


    public Dealer() {
        super("딜러", new Hand());
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
