package view.dto;

import domain.Cards;
import domain.Name;
import domain.Player;

public class PlayerDto extends ParticipantDto {

    private PlayerDto(final Name name, final Cards cards) {
        super(name, cards);
    }

    public PlayerDto(final String playerName) {
        this(new Name(playerName), null);
    }

    public PlayerDto(final Player player) {
        this(player.name(), player.cards());
    }
}
