package dto;

import domain.Dealer;
import java.util.List;

public record DealerResultDto(
        ParticipantDto dealerDto,
        int score,
        double dealerEarnMoney
) {
    public static DealerResultDto from(Dealer dealer, List<PlayerResultDto> playerResults) {
        double earnMoney = 0;

        for (PlayerResultDto playerResult : playerResults) {
            earnMoney += playerResult.playerEarnMoney() * -1;
        }

        return new DealerResultDto(ParticipantDto.from(dealer), dealer.getOwnCardsSum(), earnMoney);
    }
}