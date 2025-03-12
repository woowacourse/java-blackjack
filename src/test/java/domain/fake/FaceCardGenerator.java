package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;
import java.util.List;

public class FaceCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        return List.of(new Card(CardType.DIAMOND, CardScore.JACK), new Card(CardType.SPADE, CardScore.QUEEN));
    }
}
