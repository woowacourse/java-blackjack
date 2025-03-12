package deck;

import card.Card;
import card.CardNumber;
import card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckCreator implements DeckCreateStrategy {

    @Override
    public List<Card> createAllCards() {
        List<Card> temp = new ArrayList<>();
        for (CardShape type : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                temp.add(new Card(type, number));
            }
        }
        Collections.shuffle(temp);

        return temp;
    }
}
