package dto;

import domain.MatchResult;
import vo.Revenue;

public class MatchResultDto {
    private final MatchResult matchResult;
    private final Revenue revenue;

    private MatchResultDto(MatchResult matchResult, Revenue revenue) {
        this.matchResult = matchResult;
        this.revenue = revenue;
    }

    public static MatchResultDto of(MatchResult matchResult, double revenue) {
        return new MatchResultDto(matchResult, Revenue.from(revenue));
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public double getRevenue() {
        return revenue.getRevenue();
    }
}
