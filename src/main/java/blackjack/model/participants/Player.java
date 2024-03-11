package blackjack.model.participants;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return !cards.isBust();
    }

    public Result getResult(Cards otherCards) {
        if (cards.isBust()) {
            return Result.LOSE;
        }
        if (otherCards.isBust()) {
            return Result.WIN;
        }
        return compareScore(otherCards);
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private Result compareScore(Cards otherCards) {
        int calculatedScore = cards.getScore();
        int otherScore = otherCards.getScore();

        if (calculatedScore > otherScore) {
            return Result.WIN;
        }
        if (calculatedScore < otherScore) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }

    public String getName() {
        return name;
    }
}
