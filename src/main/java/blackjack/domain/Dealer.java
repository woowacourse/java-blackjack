package blackjack.domain;

public class Dealer extends Participant {

    private static final int DEALER_STAND_POINT = 17;


    public Dealer() {
        super("딜러", new Hand());
    }

    @Override
    public void recieveCard(Card card) {
        if (!isOver17()) {
            addCard(card);
        }
    }

    public String getFirstCardNames() {
        return getFirstCardName();
    }

    public boolean isOver17() {
        return getTotalPoint() >= DEALER_STAND_POINT;
    }


}
