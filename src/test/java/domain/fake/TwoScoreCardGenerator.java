package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;
import java.util.List;

public class TwoScoreCardGenerator implements CardGenerator {
    @Override
    public List<Card> generate() {
        return List.of(new Card(CardType.DIAMOND, CardScore.TWO), new Card(CardType.SPADE, CardScore.TWO));
    }
}
