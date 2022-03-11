package blackjack.dto;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.Match;
import java.util.Map;

public class DealerResultDto {
    private final String name;
    private final int winCount;
    private final int loseCount;
    private final int drawCount;

    public DealerResultDto(String name, int winCount, int loseCount, int drawCount) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.drawCount = drawCount;
    }

    public static DealerResultDto of(DealerResult dealerResult) {
        Map<Match, Integer> dealerMatch = dealerResult.getMatchResult();
        return new DealerResultDto(dealerResult.getName(),
                dealerMatch.get(Match.WIN),
                dealerMatch.get(Match.LOSE),
                dealerMatch.get(Match.DRAW));
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }
}
