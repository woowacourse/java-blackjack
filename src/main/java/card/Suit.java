package card;

public enum Suit {
    SPADE("♠︎"),
    CLOVER("♣︎"),
    HEART("♥︎"),
    DIAMOND("♦︎");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
