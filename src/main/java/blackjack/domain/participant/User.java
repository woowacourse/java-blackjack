package blackjack.domain.participant;

import blackjack.domain.result.Result;

import static blackjack.domain.result.ScoreCalculator.cardSum;

public class User extends Participant{

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
    private static final int BUST_STANDARD = 21;

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public Result checkResult(int otherScore) {
        return Result.checkUserResult(cardSum(cards), otherScore);
    }

    public boolean checkBust() {
        return cardSum(cards) > BUST_STANDARD;
    }

    public String getName() {
        return name;
    }
}
