package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.dto.MatchResultDto;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;
    private static final int START_CARD_SIZE = 1;

    public Dealer() {
        super(Collections.emptyList());
    }

    Dealer(List<Card> cards) {
        super(cards);
    }

    public boolean isWin(Player player) {
        if (player.isBusted() || this.isBlackjack()) {
            return true;
        }
        if (this.isBusted() || player.isBlackjack()) {
            return false;
        }
        return this.calculateScore() >= player.calculateScore();
    }

    private boolean isLose(Player player) {
        return !isWin(player);
    }

    public MatchResultDto match(Players players) {
        int winCount = (int) players.getPlayers().stream()
                .filter(this::isWin)
                .count();
        int loseCount = (int) players.getPlayers().stream()
                .filter(this::isLose)
                .count();
        return new MatchResultDto(winCount, loseCount);
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }
}
