package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

import java.util.List;

public class Participant extends Player {

    private static final int MAX_SCORE = 21;

    private Result result;

    public Participant(final List<Card> cards, final String name) {
        super(cards, name);
        validateEmpty(name);
        result = Result.LOSE;
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceOne() <= MAX_SCORE;
    }

    public void makeWin() {
        result = Result.WIN;
    }

    public Result getResult() {
        return result;
    }

}
