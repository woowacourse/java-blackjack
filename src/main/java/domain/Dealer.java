package domain;

public class Dealer extends Participant{
    public Dealer(String name) {
        super(name);
    }

    public boolean shouldGetMoreCard(){
        int result = cards.getTotalSum();
        return result <= 16;
    }

    public Card getFirstCard(){
        return cards.peek();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
