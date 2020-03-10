package blackjack.domain.card;

public enum CardType {

    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String koreanName;

    CardType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

}
