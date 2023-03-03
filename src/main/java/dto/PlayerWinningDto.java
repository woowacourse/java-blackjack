package dto;

import blackjackGame.Result;
import player.Name;
import player.Player;

public class PlayerWinningDto {
    private final Name name;
    private final Result result;

    private PlayerWinningDto(Name name, Result result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerWinningDto from(Player player) {
        return new PlayerWinningDto(player.getName(), player.getResult());
    }

    public Name getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
