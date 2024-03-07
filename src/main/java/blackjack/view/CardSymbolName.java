package blackjack.view;

import blackjack.domain.card.CardSymbol;

public enum CardSymbolName {
    SPADE("스페이드", CardSymbol.SPADE),
    DIAMOND("다이아몬드", CardSymbol.DIAMOND),
    HEART("하트", CardSymbol.HEART),
    CLUB("클로버", CardSymbol.CLUB);

    private final String name;
    private final CardSymbol cardSymbol;

    CardSymbolName(String name, CardSymbol cardSymbol) {
        this.name = name;
        this.cardSymbol = cardSymbol;
    }

    public static String convert(CardSymbol cardSymbol) {
        return valueOf(cardSymbol.name()).name;
    }
}
