package blackjack;

public class Player {

    private final String name;
    private final Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }


    public void recieveCard(Deck deck){
        if(!hand.isBust()){
            hand.addCard(deck.draw());
        }
    }

    public int getCardCount(){
        return hand.getCount();
    }


}
