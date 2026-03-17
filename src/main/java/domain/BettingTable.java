package domain;

import domain.participant.WinStatus;
import domain.vo.Money;
import domain.vo.Name;
import dto.ResultForBettingDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingTable {
    private static final double BLACKJACK_BONUS = 1.5;
    private final Map<Name, Money> bettingTable = new HashMap<>();

    public void placeBet(Name name, Money money) {
        bettingTable.put(name, money);
    }

    public Money calculateProfit(ResultForBettingDto dto) {
        if (dto.getWinStatus() == WinStatus.WIN && dto.isBlackjack()) {
            return new Money(bettingTable.get(dto.getName()).multiplyDouble(BLACKJACK_BONUS));
        }

        if (dto.getWinStatus() == WinStatus.LOSS) {
            return bettingTable.get(dto.getName()).negate();
        }

        if (dto.getWinStatus() == WinStatus.DRAW) {
            return new Money(BigDecimal.ZERO);
        }

        return bettingTable.get(dto.getName());
    }

    public Money getDealerProfit(List<ResultForBettingDto> dtos) {
        return dtos.stream()
                .map((dto) -> calculateProfit(dto))
                .reduce(new Money(BigDecimal.ZERO), Money::add)
                .negate();
    }
}
