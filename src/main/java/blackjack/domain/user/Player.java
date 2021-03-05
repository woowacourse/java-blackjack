package blackjack.domain.user;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.user.User;
import blackjack.util.StringUtil;

public class Player extends User {
    boolean isStay = false;

    public Player(String name) {
        super();
        validate(name);
        this.name = StringUtil.deleteWhiteSpaces(name);
    }

    private static void validate(String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다. 플레이어의 이름을 입력해주세요.");
        }
    }

    public ResultType generateResultAgainstDealer(int dealerScore) {
        if (this.getScore() > dealerScore) {
            return ResultType.WIN;
        }
        if (this.getScore() < dealerScore || this.getScore() == Card.BUST) {
            return ResultType.LOSE;
        }

        return ResultType.DRAW;
    }

    public void stay() {
        this.isStay = true;
    }

    public boolean isStay() {
        return this.isStay;
    }

    public boolean isBust() {
        return this.getScore() == Card.BUST;
    }
}
