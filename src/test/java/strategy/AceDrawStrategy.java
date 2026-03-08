package strategy;

import domain.vo.Card;
import domain.constant.CardMark;
import domain.constant.CardRank;
import domain.DrawStrategy;

public class AceDrawStrategy implements DrawStrategy {
    @Override
    public Card draw() {
        return new Card(CardRank.ACE, CardMark.SPADE);
    }
}
