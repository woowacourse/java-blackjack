package blackjack.dto;

import blackjack.model.BlackJackGame;
import blackjack.model.player.Gamers;
import java.util.List;
import java.util.stream.Collectors;

public class GamersDto {
    private final List<GamerDto> gamers;

    private GamersDto(Gamers gamers) {
        this.gamers = gamers.getValues().stream()
                .map(GamerDto::from)
                .collect(Collectors.toList());
    }

    public static GamersDto from(BlackJackGame game) {
        return new GamersDto(game.getGamers());
    }

    public List<GamerDto> getGamers() {
        return gamers;
    }
}
