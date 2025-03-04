package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CardSet {
    private final List<Card> cards;

    private CardSet(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardSet generateEmptySet() {
        return new CardSet(new ArrayList<>());
    }

    public static CardSet generateFullSet() {
        //52장 만들기
        List<Card> initializedCardSet = initializeCardSet();
        return new CardSet(initializedCardSet);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    private static List<Card> initializeCardSet() {
        List<Card> initCard = new ArrayList<>();
        addShapes(initCard);
        addRoles(initCard);
        return initCard;
    }

    private static void addRoles(List<Card> initCard) {
        List<CardType> roles = List.of(CardType.JACK, CardType.KING, CardType.QUEEN);
        for (CardType role : roles) {
            for (int i = 1; i <= 4; i++) {
                initCard.add(new Card(10, role));
            }
        }
    }

    private static void addShapes(List<Card> initCard) {
        List<CardType> shapes = List.of(CardType.CLOVER, CardType.HEART, CardType.SPADE, CardType.DIAMOND);
        for (CardType shape : shapes) {
            for (int number = 1; number <= 10; number++) {
                initCard.add(new Card(number, shape));
            }
        }
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardSet cardSet = (CardSet) o;
        return Objects.equals(cards, cardSet.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
