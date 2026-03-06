package blackjack;

public class Dealer {

    static private final String name="딜러";
    private final Hand hand = new Hand();

    public Dealer() {
    }

    public void recieveCard(Card card){
        if(!hand.isOver17()){
            hand.addCard(card);
        }
    }

    public int getCardCount(){
        return hand.getCount();
    }

    public String getFirstCardNames(){
        return hand.getFirstCardName();
    }

    public boolean isOver17(){
        return hand.isOver17();
    }

    public String getCardNames(){
        return hand.getCardNames();
    }

    public int getTotalPoint(){
        return hand.getTotalPoint();
    }

    public boolean isBust(){
        return hand.isBust();
    }



}
