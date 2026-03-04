package domain.card;

public enum CardSuit {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드");

    private final String suit;
    CardSuit(String suit){
        this.suit = suit;
    }

    public String getSuit(){
        return suit;
    }
}
