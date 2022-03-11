package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Participant extends Player {

    private Result winState = Result.LOSE;

    public Participant(final List<Card> cards, final String name) {
        super(cards, name);
        validateEmpty(name);
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    public void win() {
        this.winState = Result.WIN;
    }

    public boolean isOverMaxScore() {
        return getScoreByAceOne() > MAX_SCORE;
    }

    public Result getWinState() {
        return this.winState;
    }
}
