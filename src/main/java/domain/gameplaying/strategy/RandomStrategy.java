package domain.gameplaying.strategy;

import domain.gameplaying.Card;
import domain.gameplaying.CardMark;
import domain.gameplaying.CardRank;
import domain.gameplaying.DrawStrategy;
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
