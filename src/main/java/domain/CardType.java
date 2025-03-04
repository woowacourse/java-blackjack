package domain;

public enum CardType {
    SPACE,
    HEART,
    CLOVER,
    DIAMOND;

    public static CardType findByRandom(int randomIndex) {
        if(randomIndex >= CardNumberType.values().length) {
            throw new IllegalArgumentException("[ERROR] 해당하는 카드 모양의 인덱스가 존재하지 않습니다.");
        }
        return CardType.values()[randomIndex];
    }
}
