package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardType;

public class FaceCardGenerator implements CardGenerator {

    @Override
    public Card peekRandomCard() {
        return new Card(CardType.CLOVER_K);
    }
}
