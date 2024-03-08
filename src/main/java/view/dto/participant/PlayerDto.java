package view.dto.participant;

import domain.card.Cards;
import domain.participant.Name;
import domain.participant.Player;
import view.dto.card.CardsDto;

public class PlayerDto extends ParticipantDto {
    public PlayerDto(final Player player) {
        super(player.name(), new CardsDto(player.hand(), player.cardSum()));
    }

    public PlayerDto(final String playerName) {
        super(new Name(playerName), new CardsDto(new Cards(), 0));
    }
}
