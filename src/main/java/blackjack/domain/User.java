package blackjack.domain;

import blackjack.exception.UserNameEmptyException;

public class User {
    private String name;

    public User(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException("유저의 이름은 공백일 수 없습니다.");
        }
        this.name = name;
    }
}
