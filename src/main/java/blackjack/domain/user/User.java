package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.util.StringUtil;

public abstract class User {
    protected String name;
    protected Cards cards;

    public User(String name) {
        validate(name);
        this.name = StringUtil.deleteWhiteSpaces(name);
        this.cards = new Cards();
    }

    private static void validate(String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다. 이름을 입력해주세요.");
        }
    }

    public void draw(Card card) {
        this.cards.draw(card);
    }

    public int getScore() {
        return this.cards.getScore();
    }

    public boolean isBlackJack() {
        return this.cards.isBlackJack();
    }

    public boolean isBust() {
        return this.cards.isBust();
    }

    abstract boolean canContinue();

    public Cards getCards() {
        return this.cards;
    }

    public String getName() {
        return this.name;
    }
}
