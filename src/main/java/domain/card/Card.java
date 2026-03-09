package domain.card;

public class Card {
    private TrumpSuit suit;
    private TrumpNumber number;

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

    public String cardToKorName() {
        return ""+ number + suit;
    }
}
