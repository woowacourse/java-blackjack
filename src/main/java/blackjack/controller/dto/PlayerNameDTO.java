package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerNameDTO {
    private final Name name;

    public PlayerNameDTO(Player player) {
        name = player.getName();
    }

    public String getName() {
        return name.getName();
    }
}
