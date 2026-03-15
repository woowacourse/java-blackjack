package dto;

import domain.money.Money;
import domain.participant.Player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitResultDto {

    private final BigDecimal dealerProfitResult;
    private final Map<String, BigDecimal> playersProfitResult;

    public ProfitResultDto(Money dealerProfitResult, Map<Player, Money> playersProfitResult) {
        this.dealerProfitResult = dealerProfitResult.getAmount();

        this.playersProfitResult = playersProfitResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> entry.getValue().getAmount(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public BigDecimal getDealerProfitResult() {
        return dealerProfitResult;
    }

    public Map<String, BigDecimal> getPlayersProfitResult() {
        return playersProfitResult;
    }
}
