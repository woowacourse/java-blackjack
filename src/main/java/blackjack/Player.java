package blackjack;

public class Player {

    private final String name;
    private final Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }


    public void recieveCard(Card card){
        if(!hand.isBust()){
            hand.addCard(card);
        }
    }

    public int getCardCount(){
        return hand.getCount();
    }

    public String getName() {
        return name;
    }

    public String getCardNames(){
        return hand.getCardNames();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int getTotalPoint(){
        return hand.getTotalPoint();
    }

}
