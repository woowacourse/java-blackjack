package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;
import java.util.List;

public class AceCardGenerator implements CardGenerator {
    @Override
    public List<Card> generate() {
        return List.of(new Card(CardType.DIAMOND, CardScore.ACE), new Card(CardType.SPADE, CardScore.ACE));
    }
}
