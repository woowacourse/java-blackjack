package blackjack.domain;

public enum Symbol {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String KoreanName;

    Symbol(String koreanName) {
        this.KoreanName = koreanName;
    }

    public String getKoreanName() {
        return KoreanName;
    }
}
