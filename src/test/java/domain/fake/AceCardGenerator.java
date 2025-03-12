package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;

public class AceCardGenerator implements CardGenerator {
    @Override
    public Card peekCard() {
        return new Card(CardType.DIAMOND, CardScore.ACE);
    }
}
