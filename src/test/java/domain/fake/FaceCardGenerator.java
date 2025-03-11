package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;

public class FaceCardGenerator implements CardGenerator {

    @Override
    public Card peekCard() {
        return new Card(CardType.CLOVER, CardScore.JACK);
    }
}
