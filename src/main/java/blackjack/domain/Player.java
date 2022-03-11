package blackjack.domain;

import java.util.List;

public class Player extends Gamer {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public boolean isPlaying() {
        return getTotalScore() <= PLAYING_STANDARD;
    }
}
