package blackjack.domain;

public class Dealer extends Participant{


    public Dealer() {
        super("딜러",new Hand());
    }

    @Override
    public void recieveCard(Card card){
        if(!hand.isOver17()){
            hand.addCard(card);
        }
    }

    public String getFirstCardNames(){
        return hand.getFirstCardName();
    }

    public boolean isOver17(){
        return hand.isOver17();
    }





}
