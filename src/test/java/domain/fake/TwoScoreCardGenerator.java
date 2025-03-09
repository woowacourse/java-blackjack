package domain.fake;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardScore;
import domain.card.CardType;

public class TwoScoreCardGenerator implements CardGenerator {
    @Override
    public Card peekRandomCard() {
        return new Card(CardType.CLOVER, CardScore.TWO);
    }
}
