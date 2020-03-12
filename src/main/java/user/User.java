package user;

import card.Deck;

public abstract class User {
    protected Hands hands;

    abstract void hit(Deck deck);



    public boolean checkBurst() {
        return hands.isBurst();
    }
    public int handSize(){
        return hands.size();
    }
}
