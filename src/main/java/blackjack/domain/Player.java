package blackjack.domain;

import java.util.List;

public class Player extends Person {
    private static final int MAX_NAME_LENGTH = 5;

    public Player(String name) {
        super(name);
        validate(name);
    }

    private void validate(String name) {
        validateBlankName(name);
        validateNameLength(name);
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
        }
    }

    @Override
    public void addCard(Card card) {
        if (getScore() > MAX_SCORE) {
            throw new IllegalArgumentException("[ERROR] 점수가 " + MAX_SCORE + "점을 넘으면 카드를 더 뽑을 수 없습니다.");
        }
        cards.addCard(card);
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
}
