package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> FULL_CARDS = Deck.setFullCards();
    private final List<Card> cards;

    protected Deck(List<Card> cards) {
        this.cards = cards;
    }

    private static List<Card> setFullCards() {
        List<Card> cards = new ArrayList<>();
        for (CardType type : CardType.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(number -> cards.add(new Card(type, number)));
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    public static Deck withFullCards() {
        return new Deck(new ArrayList<>(FULL_CARDS));
    }

    protected static Deck withCustomCards(Card... cards) {
        return new Deck(new ArrayList<>(Arrays.stream(cards).toList()));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("카드가 더이상 존재하지 않습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
