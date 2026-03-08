package domain.card;

public class Card {
    private TrumpSuit suit;
    private TrumpNumber number;

    public Card(TrumpSuit suit, TrumpNumber number) {
        this.suit = suit;
        this.number = number;
    }

    // 카드 value 반환
    public int getValue(){
        return number.getValue();
    }

    // ACE인지 아닌지 반환
    public boolean isAce(){
        return number==TrumpNumber.ACE;
    }

    @Override
    public String toString() {
        return ""+ number + suit;
    }
}
