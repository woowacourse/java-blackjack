package blackjack.domain;

import java.util.List;

public class Player extends Gamer {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public boolean canHit() {
        return getTotalScore() <= PLAYING_STANDARD;
    }

    @Override
    public GameResult createResult(Gamer dealer) {
        if (isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        return GameResult.of(dealer.getMinusScore(getTotalScore()));
    }
}
