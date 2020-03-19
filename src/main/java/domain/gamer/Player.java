package domain.gamer;

import exception.NameFormatException;

public class Player extends Gamer {
    private static final String PATTERN = "[a-zA-Z가-힣]*";
    private static final int DRAW_CARD_PIVOT = 21;

    private Money money;

    public Player(String name, String money) {
        super(name);
        if (isInvalidName(name)) {
            throw new NameFormatException("잘못된 이름입니다.");
        }
        this.money = new Money(money);
    }

    private boolean isInvalidName(String name) {
        return !name.matches(PATTERN);
    }

    public MatchResult findMatchResult(Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.DRAW;
        }

        if (isBlackJack()) {
            return MatchResult.BLACKJACK;
        }

        if (dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }

        return MatchResult.of(score.getScore(), dealer.score.getScore());
    }

    @Override
    public boolean isDrawable() {
        return score.getScore() < DRAW_CARD_PIVOT;
    }

    public Money getMoney() {
        return money;
    }
}
