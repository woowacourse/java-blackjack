package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cardDeck = new ArrayList<>();
    private int index = 0;

    public CardDeck() {
        initCardDeck();
    }

    private void initCardDeck() {
        for (TrumpSuit suit : TrumpSuit.values()) {
            for (TrumpNumber number : TrumpNumber.values()){
                cardDeck.add(new Card(suit, number));
            }
        }

        shuffleCards();
    }

    private void shuffleCards(){
        Collections.shuffle(cardDeck);
    }

    public Card draw() {
        if (index > cardDeck.size() - 1) {
            shuffleCards();
            index = 0;
        }

        return cardDeck.get(index++);
    }
}
