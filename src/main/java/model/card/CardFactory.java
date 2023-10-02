package model.card;

import model.card.dto.CardRequest;
import model.cards.Cards;
import model.name.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.Keyword.ACE;

public class CardFactory {

    private static final List<String> CARD_TYPES = List.of("스페이드", "다이아몬드", "클로버", "하트");
    private static final List<String> SPECIAL_TYPES = List.of("J", "Q", "K");
    private static final int MIN_SCORE = 2;
    private static final int MAX_SCORE = 10;
    private static final int SPECIAL_SCORE = 11;

    private CardFactory() {

    }

    public static Cards createCards() {
        List<Card> cards = new ArrayList<>();

        for (int score = MIN_SCORE; score <= MAX_SCORE; score++) {
            cards.addAll(createNormalCards(score));
        }
        for (String special : SPECIAL_TYPES) {
            cards.addAll(createSpecialCards(special, MAX_SCORE));
        }
        cards.addAll(createSpecialCards(ACE.getValue(), SPECIAL_SCORE));

        return Cards.from(cards);
    }

    public static List<Card> createNormalCards(int score) {
        return createNormalCardsWithScore(score, CARD_TYPES);
    }

    private static List<Card> createNormalCardsWithScore(int score, final List<String> names) {
        List<Name> cardNames = Name.convertStringListToNamesWithScore(score, names);

        return cardNames.stream()
                .map(cardName -> CardRequest.createDefault(cardName.getName(), score))
                .map(Card::from)
                .collect(Collectors.toList());
    }

    public static List<Card> createSpecialCards(String special, int score) {
        return createSpecialCards(special, CARD_TYPES, score);
    }

    private static List<Card> createSpecialCards(final String special, final List<String> names, int score) {
        List<Name> cardNames = Name.convertStringListToNamesWithSpecial(special, names);

        return cardNames.stream()
                .map(cardName -> CardRequest.createDefault(cardName.getName(), score))
                .map(Card::from)
                .collect(Collectors.toList());
    }
}
