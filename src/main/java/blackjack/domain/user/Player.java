package blackjack.domain.user;

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


    public void stay() {
        this.isStay = true;
    }

    public boolean isStay() {
        return this.isStay;
    }

    public boolean isBust() {
        return this.cards.isBust();
    }
}
