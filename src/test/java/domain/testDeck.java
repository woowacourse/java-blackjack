package domain;

import java.util.ArrayList;
import java.util.List;

public class testDeck {
    private final List<Card> cards;

    public testDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static testDeck createDeck() {
        List<Card> standardCards = Cards.createCards();
        List<Card> shuffledCards = Cards.shuffleCards(standardCards);
        return new testDeck(shuffledCards);
    }

    public static Integer getDeckSize(testDeck testDeck) {
        return testDeck.cards.size();
    }

    // TODO: Deck에 카즈가 있으니까. - 0장이 되었을 때 예외처리
    // TODO: 카드를 보관하고 섞고, 한 장씩 나눠주는 역할을 한다.
    public Card distributeCard() {
        Card card = cards.removeFirst();
        return card;
    }
}
