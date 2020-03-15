package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class User extends Player {
    public static final String USER_NAME_EMPTY_EXCEPTION_MESSAGE = "유저의 이름은 공백일 수 없습니다.";

    public User(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(USER_NAME_EMPTY_EXCEPTION_MESSAGE);
        }
        this.name = name;
    }

    public boolean isNoneStatus() {
        return this.cards.isNoneStatus();
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.getCards().subList(START_INDEX, INITIAL_CARDS_SIZE);
    }
}
