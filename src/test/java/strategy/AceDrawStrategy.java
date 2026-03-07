package strategy;

import domain.Card;
import domain.CardMark;
import domain.CardRank;
import domain.DrawStrategy;

public class AceDrawStrategy implements DrawStrategy {
    @Override
    public Card draw() {
        return new Card(CardRank.ACE, CardMark.SPADE);
    }
}
