package domain;

public class Participants {
    private final String name;
    private final Hand hand;

    public Participants(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void keepCard(Card card){
        hand.addCard(card);
    }

    public Integer handSize(){
        return hand.getHandSize();
    }

    public Integer getTotalCardScore(){
        return hand.calculateTotalScore();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
