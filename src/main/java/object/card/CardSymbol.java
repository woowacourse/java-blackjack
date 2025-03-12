package object.card;

public enum CardSymbol {
    HEART("♥"),
    DIAMOND("◆"),
    SPADE("♠"),
    CLOVER("♣");

    private static final String red      = "\u001B[31m" ;
    private static final String cyan     = "\u001B[36m" ;
    private static final String exit     = "\u001B[0m" ;

    private final String name;

    CardSymbol(final String name) {
        this.name = name;
    }

    public String getName() {
        if (this == CardSymbol.HEART || this == CardSymbol.DIAMOND) {
            return red + name + exit;
        }
        return cyan + name + exit;
    }
}
