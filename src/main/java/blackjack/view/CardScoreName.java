package blackjack.view;

import blackjack.domain.card.CardScore;

public enum CardScoreName {
    TWO("2", CardScore.TWO),
    THREE("3", CardScore.THREE),
    FOUR("4", CardScore.FOUR),
    FIVE("5", CardScore.FIVE),
    SIX("6", CardScore.SIX),
    SEVEN("7", CardScore.SEVEN),
    EIGHT("8", CardScore.EIGHT),
    NINE("9", CardScore.NINE),
    JACK("J", CardScore.JACK),
    QUEEN("Q", CardScore.QUEEN),
    KING("K", CardScore.KING),
    ACE("A", CardScore.ACE);

    private final String name;
    private final CardScore cardScore;

    CardScoreName(String name, CardScore cardScore) {
        this.name = name;
        this.cardScore = cardScore;
    }

    public static String convert(CardScore cardScore) {
        return valueOf(cardScore.name()).name;
    }
}
