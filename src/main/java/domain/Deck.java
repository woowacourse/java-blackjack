package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// card를 가지고 일급 컬렉션을 만든 느낌이다. Deck이.
// deck이 cards를 갖고 있으면
// cards는 플레이어들이 갖고있는 카드.
// card -> cards와 deck을 만든다.
public class Deck {
    private final List<Card> cards;

    public Deck() {
        List<Card> beforeShuffledCards = generateDeck();
        this.cards = shuffleCards(beforeShuffledCards);
    }

    public Card distributeCard() {
        Card card = cards.removeFirst();
        return card;
    }

    private List<Card> generateDeck() {
        List<Card> cards = new ArrayList<>();

        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        return cards;
    }

    // TODO: 변수명 추후 수정
    private List<Card> shuffleCards(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }


}
