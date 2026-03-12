package blackjack.domain.participant;

import static blackjack.util.constant.Constants.MAX_LENGTH;

public record UserName(
        String name
) {

    public UserName {
        validate(name);
        name = name.strip();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 공백이 될 수 없습니다.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 5자가 넘을 수 없습니다.");
        }
    }
}
