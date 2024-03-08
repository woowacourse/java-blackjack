package domain.participant;

import view.dto.participant.PlayerDto;

public class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    public Player(final PlayerDto playerDto) {
        this(new Name(playerDto.name()));
    }
}
