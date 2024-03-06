package domain;

import view.dto.PlayerDto;

public class Player extends Participant {

    private final Name name;

    public Player(final Name name) {
        super();
        this.name = name;
    }

    public Player(final PlayerDto playerDto){
        this(new Name(playerDto.name()));
    }
}
