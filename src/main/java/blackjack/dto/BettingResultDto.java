package blackjack.dto;

import blackjack.domain.BettingResult;

public record BettingResultDto(String name, int betting) {
   public static BettingResultDto from(final BettingResult bettingResult) {
       return new BettingResultDto(bettingResult.getName().getName(), bettingResult.getBetting());
    }
}
