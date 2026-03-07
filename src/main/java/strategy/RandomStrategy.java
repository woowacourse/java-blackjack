package strategy;

import domain.Card;
import domain.CardMark;
import domain.CardRank;
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
