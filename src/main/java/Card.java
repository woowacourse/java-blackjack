public class Card {
    private final Symbol symbol;
    private final CardNumber cardNumber;

    public Card(Symbol symbol, CardNumber cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public int getScore() {
        return this.cardNumber.getScore();
    }

    public String getText() {
        return this.cardNumber.getNumberToPrint() + this.symbol.getName();
    }
}
