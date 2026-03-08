package domain.strategy;

import domain.vo.Card;
import domain.constant.CardMark;
import domain.constant.CardRank;
import domain.DrawStrategy;
import java.util.Random;

public class RandomStrategy implements DrawStrategy {
    @Override
    public Card draw() {
        return new Card(
                CardRank.values()[new Random().nextInt(CardRank.values().length)],
                CardMark.values()[new Random().nextInt(CardMark.values().length)]
        );
    }
}
