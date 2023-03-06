package dto;

import java.util.Map;

import blackjackgame.Result;
import player.Count;
import player.Dealer;
import player.Name;

public class DealerWinningDto {
    private final Name name;
    private final Map<Result, Count> winningMap;

    private DealerWinningDto(Name name, Map<Result, Count> winningMap) {
        this.name = name;
        this.winningMap = winningMap;
    }

    public static DealerWinningDto from(Dealer dealer) {
        return new DealerWinningDto(dealer.getName(), dealer.getDealerResult());
    }

    public Name getName() {
        return name;
    }

    public Map<Result, Count> getWinningMap() {
        return winningMap;
    }
}
