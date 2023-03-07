package blackjack.dto;

import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.result.Result;

public class ResultDto {

    private final String name;
    private final String result;

    public ResultDto(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public static ResultDto of(Player player, Result result) {
        return new ResultDto(player.getName().getName(), result.getResult());
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}
