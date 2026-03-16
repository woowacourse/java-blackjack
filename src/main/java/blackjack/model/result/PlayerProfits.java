package blackjack.model.result;

import blackjack.common.error.ErrorCode;
import blackjack.dto.DealerProfitDto;
import blackjack.dto.PlayerProfitDto;
import blackjack.dto.PlayerProfitsDto;
import blackjack.model.money.Money;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerProfits {

    private final Map<Player, Money> playerProfits;

    public PlayerProfits(Map<Player, Money> playerProfits) {
        if (playerProfits == null || playerProfits.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.NULL_OR_EMPTY_RESULT.getMessage());
        }
        this.playerProfits = playerProfits;
    }

    public static PlayerProfits of(List<Player> players, Dealer dealer) {
        Map<Player, Money> profits = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.getPlayerProfit(dealer)));

        return new PlayerProfits(profits);
    }

    public DealerProfitDto getDealerProfit() {
        Money sum = Money.ZERO;
        for (Money playerProfit : playerProfits.values()) {
            sum = sum.minus(playerProfit);
        }

        return new DealerProfitDto(sum);
    }

    public PlayerProfitsDto getPlayerResults() {
        List<PlayerProfitDto> results = playerProfits.entrySet().stream()
                .map(entry -> PlayerProfitDto.from(entry.getKey(), entry.getValue()))
                .toList();

        return PlayerProfitsDto.from(results);
    }
}
