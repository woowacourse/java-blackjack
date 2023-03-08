package domain.card;

public enum Shape {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");
    
    private final String symbol;
    
    Shape(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
}
