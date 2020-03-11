package blackjack.domain;

import blackjack.exception.UserNameEmptyException;

import java.util.List;

public class User extends Player {
    private static final int USER_INITIAL_CARDS_SIZE = 2;

    public User(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException("유저의 이름은 공백일 수 없습니다.");
        }
        this.name = name;
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.subList(START_INDEX, USER_INITIAL_CARDS_SIZE);
    }
}
