package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {
    List<CardType> cardTypes;

    public RandomCardGenerator(){
        cardTypes = new ArrayList<>(CardType.getCardTypes());
        Collections.shuffle(cardTypes);
    }

    @Override
    public Card peekRandomCard() {
        final CardType cardType = cardTypes.removeFirst();
        return new Card(cardType);
    }
}
