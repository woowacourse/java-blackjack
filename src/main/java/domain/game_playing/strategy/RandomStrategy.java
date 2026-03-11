package domain.game_playing.strategy;

import domain.game_playing.Card;
import domain.game_playing.CardMark;
import domain.game_playing.CardRank;
import domain.game_playing.DrawStrategy;
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
