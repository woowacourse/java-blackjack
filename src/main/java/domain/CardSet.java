package domain;

import java.util.ArrayList;
import java.util.List;

public class CardSet {
    private final List<Card> cards;

    public CardSet(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static CardSet generateFullSet() {
        //52장 만들기
        List<Card> initializedCardSet = initializeCardSet();
        return new CardSet(initializedCardSet);
    }

    private static List<Card> initializeCardSet() {
        List<Card> initCard = new ArrayList<>();
        List<CardType> shapes = List.of(CardType.CLOVER, CardType.HEART, CardType.SPADE, CardType.DIAMOND);
        for (CardType shape : shapes) {
            for (int number = 1; number <= 10; number++) {
                initCard.add(new Card(number, shape));
            }
        }

        // J, K, Q
        List<CardType> roles = List.of(CardType.JACK, CardType.KING, CardType.QUEEN);
        for (CardType role : roles) {
            for (int i = 0; i < 4; i++) {
                initCard.add(new Card(10, role));
            }
        }

        return initCard;
    }

    public List<Card> getCards() {
        return cards;
    }
}
