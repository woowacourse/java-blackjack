package blackjack.domain;

import java.util.List;

public class Player extends Gamer {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public GameResult createResult(int dealerScore) {
        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        if (dealerScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore() - dealerScore);
    }

    public boolean isPlaying() {
        return getTotalScore() <= PLAYING_STANDARD;
    }
}
