package domain.game_playing.strategy;

import domain.game_playing.Card;
import domain.game_playing.CardMark;
import domain.game_playing.CardRank;
import domain.game_playing.DrawStrategy;

public class AceDrawStrategy implements DrawStrategy {
    @Override
    public Card draw() {
        return new Card(CardRank.ACE, CardMark.SPADE);
    }
}
