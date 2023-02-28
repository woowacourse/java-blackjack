package blackjack.domain;

public enum CardSuit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    final String cardSuitName;

    CardSuit(String cardSuitName) {
        this.cardSuitName = cardSuitName;
    }

    public String getCardSuitName() {
        return cardSuitName;
    }
}
