package blackjack.controller.dto;

import blackjack.domain.player.Name;
import blackjack.domain.player.User;

public class UserNameDTO {
    private final Name name;

    public UserNameDTO(User user) {
        name = user.getName();
    }

    public String getName() {
        return name.getName();
    }
}
