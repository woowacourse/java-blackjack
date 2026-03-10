package domain.card;

public class Card {
    private final TrumpSuit suit;
    private final TrumpNumber number;

    public Card(TrumpSuit suit, TrumpNumber number) {
        this.suit = suit;
        this.number = number;
    }

    public int getValue(){
        return number.getValue();
    }

    public boolean isAce(){
        return number == TrumpNumber.ACE;
    }

    @Override
    public String toString() {
        return ""+ number + suit;
    }
}
