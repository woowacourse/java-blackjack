package blackjack;

public class Dealer {

    static private final String name="딜러";
    private final Hand hand = new Hand();

    public Dealer() {
    }

    public void recieveCard(Deck deck){
        if(!hand.isOver17()){
            hand.addCard(deck.draw());
        }
    }

    public int getCardCount(){
        return hand.getCount();
    }

    public String getFirstCardNames(){
        return hand.getFirstCardName();
    }

}
