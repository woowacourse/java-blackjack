package blackjack.util;

import java.util.stream.Stream;

public enum CardNumber {
    ACE("A",1),
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",10),
    QUEEN("Q",10),
    KING("K",10),
    ;

    final int value;
    final String name;

    CardNumber(String name , int value) {
        this.name = name;
        this.value = value;
    }

    public static CardNumber getCardNumber(CardNumber cardNumber) {
        return Stream.of(CardNumber.values())
            .filter(number -> number.equals(cardNumber))
            .findFirst()
            .get();
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
