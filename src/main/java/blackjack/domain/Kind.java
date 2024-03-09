package blackjack.domain;

public enum Kind {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String kindName;

    Kind(String kindName) {
        this.kindName = kindName;
    }

    public static Kind findKind(int kindNumber) {
        if (kindNumber < 0 || kindNumber >= values().length) {
            throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
        }
        return values()[kindNumber];
    }

    public String getKindName() {
        return kindName;
    }
}
