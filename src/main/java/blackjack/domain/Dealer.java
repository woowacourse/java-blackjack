package blackjack.domain;

public class Dealer extends Participant {

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


}
