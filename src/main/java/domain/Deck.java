package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        var cards = new ArrayList<Card>();
        var faces = List.of("heart", " spade", "diamond", "clover");
        var letters = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J");

        for (String face : faces) {
            for (String letter : letters) {
                cards.add(new Card(face, letter));
            }
        }
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
        return cards.remove(0);
    }
}
