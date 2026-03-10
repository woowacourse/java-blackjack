package domain;

import domain.card.Card;

import java.util.List;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(String name){
        this.name = name;
        hand = new Hand();
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public boolean canHit() {
        return hand.isLessThanBlackJack();
    }

    public boolean canNotHit(){
        return hand.isLargerThanEqualToBlackJack();
    }

    public int getScore() {
        return hand.getSum();
    }

    public List<Card> getCards(){
        return hand.getCards();
    }

    public int getHandSize(){
        return hand.getSize();
    }

    public String getName() {
        return name;
    }
}
