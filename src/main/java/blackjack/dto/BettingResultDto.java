package blackjack.dto;

import blackjack.domain.betting.DealerBetting;
import blackjack.domain.betting.PlayerBetting;

public record BettingResultDto(String name, int betting) {
   public static BettingResultDto from(final PlayerBetting bettingResult) {
       return new BettingResultDto(bettingResult.getName().getName(), bettingResult.getBetting());
    }

    public static BettingResultDto from(final DealerBetting dealerBettingResult) {
        return new BettingResultDto(dealerBettingResult.getName().getName(), dealerBettingResult.getBettingMoney().getMoeney());
    }
}
