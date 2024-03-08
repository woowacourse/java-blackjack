package view.dto;

import domain.Cards;
import domain.Name;
import domain.Player;

public class PlayerDto extends ParticipantDto {
    public PlayerDto(final Player player) {
        super(player.name(), new CardsDto(player.cards(), player.cardSum()));
    }

    public PlayerDto(final String playerName) {
        super(new Name(playerName), new CardsDto(new Cards(), 0));
    }
}
