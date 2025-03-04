package domain;

public enum CardNumberType {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    public static CardNumberType findByRandom(int randomIndex) {
        if(randomIndex >= CardNumberType.values().length) {
            throw new IllegalArgumentException("[ERROR] 해당하는 카드 숫자의 인덱스가 존재하지 않습니다.");
        }
        return CardNumberType.values()[randomIndex];
    }
}
