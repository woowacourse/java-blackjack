package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public Card generate() {
        List<CardType> cardTypes = new ArrayList<>(CardType.getCardTypes());
        Collections.shuffle(cardTypes);
        final CardType cardType = cardTypes.removeFirst();
        return new Card(cardType);
    }
}
