package model.deck;

import model.card.Card;
import model.cards.Cards;
import model.dice.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private static final List<String> CARD_TYPES = List.of("스페이드", "다이아몬드", "클로버", "하트");
    private static final List<String> SPECIAL_TYPES = List.of("A", "J", "Q", "K");
    private static final int MIN_SCORE = 2;
    private static final int MAX_SCORE = 10;
    private static final int SPECIAL_SCORE = 11;

    private final Cards cards;

    public Deck() {
        this.cards = createCards();
    }

    private Cards createCards() {
        List<Card> cards = new ArrayList<>();

        for (int score = MIN_SCORE; score <= MAX_SCORE; score++) {
            cards.addAll(createNormalCards(score));
        }
        for (String special : SPECIAL_TYPES) {
            cards.addAll(createSpecialCards(special, SPECIAL_SCORE));
        }

        return Cards.from(cards);
    }

    public static List<Card> createNormalCards(int score) {
        return Cards.createNormalCardsWithScore(score, CARD_TYPES);
    }

    public static List<Card> createSpecialCards(String special, int score) {
        return Cards.createSpecialCards(special, CARD_TYPES, score);
    }

    public List<Card> getCardsFromDeckAsMuchAs(int count) {
        return IntStream.range(0, count)
                .mapToObj(x -> getRandomCardFromDeck())
                .collect(Collectors.toList());
    }

    private Card getRandomCardFromDeck() {
        int index = Dice.getRandomIndex(cards.getCardSize());
        return cards.useCard(index);
    }
}
