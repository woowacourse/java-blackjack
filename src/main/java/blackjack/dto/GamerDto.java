package blackjack.dto;

import blackjack.model.BlackJackGame;
import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.List;

public class GamerDto extends PlayerDto {
    private final String name;

    private GamerDto(Player gamer) {
        super(gamer);
        this.name = gamer.getName();
    }

    public static GamerDto from(Player gamer) {
        return new GamerDto(gamer);
    }

    public String getName() {
        return name;
    }
}
