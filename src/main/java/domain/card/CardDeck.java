package domain.card;

import java.util.*;

public class CardDeck {
    private final Deque<Card> cardDeck = new ArrayDeque<>();

    public CardDeck() {
        initCardDeck();
    }

    private void initCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (TrumpSuit suit : TrumpSuit.values()) {
            for (TrumpNumber number : TrumpNumber.values()){
                cards.add(new Card(suit, number));
            }
        }
        Collections.shuffle(cards);

        cardDeck.addAll(cards);
    }


    public Card draw() {
        return cardDeck.pop();
    }
}
