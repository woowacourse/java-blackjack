package blackjack.dto;

import blackjack.domain.user.User;

public class UserRequestDto {
    private String name;
    private long money;

    public UserRequestDto(String name, long money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public long getMoney() {
        return money;
    }

    public User createUser() {
        return new User(name, money);
    }
}
