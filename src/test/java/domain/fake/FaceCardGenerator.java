package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardType;

public class FaceCardGenerator implements CardGenerator {

    @Override
    public Card generate() {
        return new Card(CardType.CLOVER_K);
    }
}
