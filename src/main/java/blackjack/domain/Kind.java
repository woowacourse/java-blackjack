package blackjack.domain;

public enum Kind {

    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    public static Kind findKind(int kindNumber) {
        if (kindNumber < 0 || kindNumber >= values().length) {
            throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
        }
        return values()[kindNumber];
    }
}
