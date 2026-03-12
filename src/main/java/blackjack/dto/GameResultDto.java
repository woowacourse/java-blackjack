package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import java.util.List;

public record GameResultDto(
    DealerResultDto dealerResultDto,
    List<GameResultDtos> gameResultDtos
) {
    public static GameResultDto from(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<GameResultDtos> gameResultDtos = participants.getPlayers().stream()
            .map(player -> convertFrom(player, dealer))
            .toList();

        DealerResultDto dealerResultDto = DealerResultDto.from(gameResultDtos);
        return new GameResultDto(dealerResultDto, gameResultDtos);
    }

    private static GameResultDtos convertFrom(Player player, Dealer dealer) {
        GameResult result = dealer.judgeAgainst(player);
        return GameResultDtos.of(player, result);
    }
}
