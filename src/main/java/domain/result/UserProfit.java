package domain.result;

import java.math.BigDecimal;

public record UserProfit(RoundBetInfo roundBetInfo, GameResult gameResult, BigDecimal profit) {}
