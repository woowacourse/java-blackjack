package player.dto;

import card.Cards;
import player.Name;
import player.Player;

public record SinglePlayerStatusDto(Name name, Cards cards) {

    public static SinglePlayerStatusDto from(Player player) {
        return new SinglePlayerStatusDto(player.getName(), player.getCards());
    }
}
