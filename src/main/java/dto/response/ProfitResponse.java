package dto.response;

import domain.Money;

public record ProfitResponse(String name, long amount) {
    public static ProfitResponse from(String name, Money money) {
        return new ProfitResponse(name, money.amount());
    }
}
