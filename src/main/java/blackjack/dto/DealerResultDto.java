package blackjack.dto;

import blackjack.domain.player.Player;
import blackjack.domain.result.Rank;
import blackjack.domain.result.Result;
import java.util.Map;

public class DealerResultDto {

    private final String name;
    private final int winCount;
    private final int drawCount;
    private final int loseCount;

    public DealerResultDto(Result result, Player dealer) {
        this.name = dealer.getName();
        Map<Rank, Integer> dealerResult = result.getDealerResult();
        this.winCount = dealerResult.getOrDefault(Rank.WIN, 0);
        this.drawCount = dealerResult.getOrDefault(Rank.DRAW, 0);
        this.loseCount = dealerResult.getOrDefault(Rank.LOSE, 0);
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
