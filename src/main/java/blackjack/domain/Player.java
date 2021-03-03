package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.util.StringUtil;

public class Player extends User {
    boolean isStay = false;

    public Player(String name) {
        super();
        validate(name);
        this.name = name;
    }

    private static void validate(String name) {
        if (StringUtil.deleteWhiteSpaces(name).equals("")) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다. 플레이어의 이름을 입력해주세요.");
        }
    }

    public ResultType decisionResult(int score) {
        if(this.getScore() > score) {
            return ResultType.WIN;
        }

        if(this.getScore() < score) {
            return ResultType.LOSE;
        }

        return ResultType.DRAW;
    }

    public void stay() {
        this.isStay = true;
    }

    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isStay() {
        if (this.isStay || getScore() == Card.BUST) {
            return true;
        }
        return false;
    }
}
