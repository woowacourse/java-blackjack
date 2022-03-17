package blackjack.domain.result;

import blackjack.domain.player.Player;

public class Score {
    private static final int BLACK_JACK_TARGET_SCORE = 21;
    private static final int DEALER_ADD_CARD_LIMIT_SCORE = 16;
    private static final int DIFFERENCE_IN_ACE_SCORE = 10;

    private final Player player;

    public Score(Player player) {
        this.player = player;
    }

    public boolean CanAddPlayerCard() {
        return player.getTotalScore() <= BLACK_JACK_TARGET_SCORE;
    }

    public boolean CanAddDealerCard() {
        return player.getTotalScore() <= DEALER_ADD_CARD_LIMIT_SCORE;
    }

    public boolean isBlackJack() {
        return player.getTotalScore() == BLACK_JACK_TARGET_SCORE && player.hasTwoCards();
    }

    public boolean isBust() {
        return player.getTotalScore() > BLACK_JACK_TARGET_SCORE;
    }

    public int getTotal() {
        int totalScore = player.getTotalScore();

        for (int i = 0; i < player.countAce(); i++) {
            totalScore = changeAceScore(totalScore);
        }

        return totalScore;
    }

    private int changeAceScore(int totalScore) {
        if (totalScore > BLACK_JACK_TARGET_SCORE) {
            totalScore -= DIFFERENCE_IN_ACE_SCORE;
        }
        return totalScore;
    }
}
