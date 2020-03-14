package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.exception.UserNameEmptyException;

import java.util.List;

public class User extends Player {
    private static final int USER_INITIAL_CARDS_SIZE = 2;
    private static final String USER_NAME_EMPTY_EXCEPTION_MESSAGE = "유저의 이름은 공백일 수 없습니다.";

    public User(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException(USER_NAME_EMPTY_EXCEPTION_MESSAGE);
        }
        this.name = name;
        this.status = Status.NONE;
    }

    public boolean isNoneStatus() {
        return this.status == Status.NONE;
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.subList(START_INDEX, USER_INITIAL_CARDS_SIZE);
    }
}
