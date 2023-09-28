package model;

import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards from(final List<Card> cards) {
        return new Cards(cards);
    }

    public static List<Card> createNormalCardsWithScore(int score, final List<String> names) {
        List<Name> cardNames = Name.convertStringListToNamesWithScore(score, names);

        return cardNames.stream()
                .map(cardName -> Card.createDefault(cardName, score))
                .collect(Collectors.toList());
    }

    public static List<Card> createSpecialCards(final String special, final List<String> names, int score) {
        List<Name> cardNames = Name.convertStringListToNamesWithSpecial(special, names);

        return cardNames.stream()
                .map(cardName -> Card.createDefault(cardName, score))
                .collect(Collectors.toList());
    }
}
