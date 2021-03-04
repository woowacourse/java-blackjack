package blackjack.domain.gamer;

import blackjack.domain.Score;
import blackjack.domain.card.Cards;

public class Player extends Person {

    private static final int MIN_NAME_LENGTH = 1;

    public Player(String name) {
        validateNameLength(name);
        super.name = name;
        super.cards = new Cards();
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("유효하지 않은 플레이어 이름입니다.");
        }
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(cards) < Score.maxScore;
    }
}
