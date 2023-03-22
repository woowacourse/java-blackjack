package card;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final ShuffleMachine shuffleMachine;
    private final List<Card> cards;

    public Deck(ShuffleMachine shuffleMachine) {
        this.shuffleMachine = shuffleMachine;
        this.cards = generateDeck();
    }

    private List<Card> generateDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Denomination value : Denomination.values()) {
                cards.add(new Card(value, suit));
            }
        }
        return new ArrayList<>(cards);
    }

    public Card draw() {
        shuffleMachine.shuffle(cards);
        if (cards.size() == 0) {
            throw new IllegalStateException("남은 카드가 없습니다.");
        }
        return cards.remove(0);
    }
}
