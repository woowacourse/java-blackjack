package blackjack.domain;

import java.util.List;

public class Player extends Person {
    private static final int MAX_NAME_LENGTH = 5;
    private static final List<String> BLACKLIST = List.of("딜러");

    public Player(String name) {
        super(name);
        validate(name);
    }

    private void validate(String name) {
        validateBlankName(name);
        validateBlacklist(name);
        validateNameLength(name);
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void validateBlacklist(String name) {
        if (BLACKLIST.contains(name)) {
            throw new IllegalArgumentException("[ERROR] " + name + "는 사용할 수 없는 이름입니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
        }
    }

    public GameResult matchGame(Person dealer) {
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (dealer.isBlackjack() && this.isBlackjack()) {
            return GameResult.WIN;
        }
        if (isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (isBust()) {
            return GameResult.LOSE;
        }
        return compareScoreWith(dealer);
    }

    private GameResult compareScoreWith(Person dealer) {
        int myScore = this.getScore();
        int dealerScore = dealer.getScore();
        if (myScore < dealerScore) {
            return GameResult.LOSE;
        }
        if (myScore > dealerScore) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public boolean canDrawCard() {
        return getScore() < MAX_SCORE;
    }
}
