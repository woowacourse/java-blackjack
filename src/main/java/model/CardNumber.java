package model;

public enum CardNumber {
    A("A"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Ten("10"),
    Queen("Q"),
    King("K"),
    Jack("J");

    private final String number;

    CardNumber(String number) {
        this.number = number;
    }
}
