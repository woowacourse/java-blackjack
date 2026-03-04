package domain;

public class Card {
    private CardNumber cardNumber;
    private CardShape cardShape;

    public Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public int getScore() {
        if(cardNumber == CardNumber.ACE){
            return 1;
        }
        if(cardNumber == CardNumber.JACK || cardNumber == CardNumber.QUEEN || cardNumber == CardNumber.KING) {
            return 10;
        }
        return Integer.parseInt(cardNumber.getNumber());
    }
}
