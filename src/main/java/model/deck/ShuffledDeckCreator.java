package model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardType;

public class ShuffledDeckCreator implements DeckCreateStrategy {

    @Override
    public List<Card> createAllCards() {
        List<Card> temp = new ArrayList<>();
        for (CardType type : CardType.values()) {
            for (CardNumber number : CardNumber.values()) {
                temp.add(new Card(type, number));
            }
        }
        Collections.shuffle(temp);

        return temp;
    }
}
